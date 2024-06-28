package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.agc.IOpinionService;
import com.fotograbados.springv1.domain.service.gproducto.IProductService;
import com.fotograbados.springv1.domain.service.ventas.IBillService;
import com.fotograbados.springv1.domain.service.ventas.IOrderService;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.agc.OpinionProduct;
import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.persistence.entities.ventas.BillEntity;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/sale")
public class SaleController {

    private final Logger log = LoggerFactory.getLogger(SaleController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IOrderService orderService;

    @Autowired
    private IBillService billService;

    @Autowired
    private IProductService productService;

    @Autowired
    private IOpinionService opinionService;


    private OrderEntity order = new OrderEntity();
    private List<BillEntity> detalles = new ArrayList<>();

    @PostMapping("/cart")
    public String addCart(@RequestParam Long idProduct, @RequestParam Integer cantidad, Model model, HttpSession session) {
        Optional<Products> optionalProducts = productService.get(idProduct);

        if (!optionalProducts.isPresent()) {
            log.error("Producto no encontrado: {}", idProduct);
            return "error"; // O redirige a una página de error adecuada
        }

        Products products = optionalProducts.get();
        log.info("Producto añadido: {}", products);
        log.info("Cantidad: {}", cantidad);

        BillEntity billEntity = new BillEntity();
        billEntity.setCantidad(cantidad);
        billEntity.setCostoUnidad(products.getPrecio());
        billEntity.setProductoNombre(products.getNombreProducto());
        billEntity.setTotal(products.getPrecio() * cantidad);
        billEntity.setProducto(products);

        // Validar que el producto no se añada dos veces
        Long idProducto = products.getIdProducto();
        boolean ingresado = detalles.stream().anyMatch(p -> p.getProducto().getIdProducto().equals(idProducto));

        if (!ingresado) {
            detalles.add(billEntity);
        }

        double sumaTotal = detalles.stream().mapToDouble(BillEntity::getTotal).sum();
        order.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", order);

        return "usuario/carrito";
    }

    @GetMapping("/delete/cart/{idProducto}")
    public String deleteProductoCart(@PathVariable Long idProducto, Model model) {
        detalles.removeIf(bill -> bill.getProducto().getIdProducto().equals(idProducto));

        double sumaTotal = detalles.stream().mapToDouble(BillEntity::getTotal).sum();
        order.setTotal(sumaTotal);
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", order);

        return "usuario/carrito";
    }

    @GetMapping("/getCart")
    public String getCart(Model model, HttpSession session) {
        // Agrega los detalles del carrito
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", order);
        //Session
        model.addAttribute("sesion", session.getAttribute("idusuario"));

        // Agrega las opiniones de productos con información de usuarios
        List<OpinionProduct> opinionProducts = opinionService.findAll();
        model.addAttribute("opinionProducts", opinionProducts);

        return "/usuario/carrito";
    }


    @GetMapping("/order")
    public String order(Model model, HttpSession session) {

        Users users = usuarioService.findById( Long.parseLong(session.getAttribute("idUsuario").toString())).get();
//        Object idUsuarioObj = session.getAttribute("idusuario");
//        if (idUsuarioObj == null) {
//            log.error("idusuario no presente en la sesión");
//            return "redirect:/usuario/log-in";
//        }
//
//        Long idUsuario = Long.parseLong(usuarioService.toString());
//        Optional<Users> optionalUser = usuarioService.findById(idUsuario);
//        if (!optionalUser.isPresent()) {
//            log.error("Usuario no encontrado con id: {}", idUsuario);
//            return "error"; // O redirige a una página de error adecuada
//        }

//        Users users = new Users();
//        Users users = optionalUser.get();
        model.addAttribute("cart", detalles);
        model.addAttribute("orden", order);
        model.addAttribute("usuario", users);

        return "usuario/resumenorden";
    }

    @GetMapping("/saveOrder")
    public String saveOrder(HttpSession session) throws Exception {
        try {
            Date fechaCreacion = new Date();
            order.setFechaVenta(fechaCreacion);
            order.setNumero(orderService.generarNumeroOrden());

            Users users = usuarioService.findById(Long.parseLong(session.getAttribute("idUsuario").toString())).orElseThrow(() -> new Exception("Usuario no encontrado"));
            order.setUsers(users);

            // Guardar la orden
            OrderEntity savedOrder = orderService.save(order);

            // Procesar y guardar los detalles de la orden (facturas)
            for (BillEntity dt : detalles) {
                dt.setOrder(savedOrder); // Asociar la factura con la orden guardada
                Products product = dt.getProducto();
                int cantidadSolicitada = dt.getCantidad();

                // Actualizar la cantidad disponible del producto
                int nuevaCantidad = product.getCantidad() - cantidadSolicitada;
                if (nuevaCantidad < 0) {
                    throw new Exception("No hay suficientes productos disponibles para la factura: " + dt.getIdfactura());
                }
                product.setCantidad(nuevaCantidad);
                productService.save(product); // Guardar el producto actualizado

                // Guardar la factura
                billService.save(dt);
            }

            // Limpiar lista y orden
            order = new OrderEntity();
            detalles.clear();

            return "redirect:/home";
        } catch (Exception e) {
            // Manejar la excepción apropiadamente
            e.printStackTrace(); // O bien, registra el error de alguna manera
            throw new Exception("Error al procesar la orden: " + e.getMessage());
        }
    }

}
