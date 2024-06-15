package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.gproducto.IProductService;
import com.fotograbados.springv1.persistence.entities.inventario.DetailProduct;
import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class HomeController {

    //Resources service
    private static final Logger log = LoggerFactory.getLogger(HomeController.class);
    @Autowired
    private IProductService productService;

    //To lay up to detail of order.
    List<DetailProduct> detalles = new ArrayList<>();

    //Data to orden
    OrderEntity order = new OrderEntity();

                    //ANNOTATIONS
    @GetMapping("")
    public String ladingp(){
        return "usuario/lading";
    }
    @GetMapping("/home")
    public String home(Model model, HttpSession session){
        log.info("sesion del usuario: {}", session.getAttribute("idUsuario"));

        model.addAttribute("products", productService.findAll());
        //Session
        //model.addAttribute("sesion", session.getAttribute("idusuario"));

        return "usuario/home";
    }
    @GetMapping("/review")
    public String review(){
        return "usuario/review";
    }

    @GetMapping("/productosShow/{idProducto}")
    public String productShow(@PathVariable Long idProducto, Model model){
        log.info("Id producto enviado como parametro {}", idProducto);
        Products products = new Products();
        Optional<Products> productsOptional = productService.get(idProducto);
        products = productsOptional.get();

        model.addAttribute("products", products);
    return "administrador/productoShowA";
    }

    @GetMapping("productohome/{idProducto}")
    public String productoHome(@PathVariable("idProducto") Long idProducto, Model model) {
        log.info("Id producto enviado como parametro {}", idProducto);
        Optional<Products> productsOptional = productService.get(idProducto);

        if (productsOptional.isPresent()) {
            Products products = productsOptional.get();
            model.addAttribute("products", products);
            return "usuario/productohome";
        } else {
            log.error("Producto con id {} no encontrado", idProducto);
            return "error/404"; // Asegúrate de tener una página de error 404
        }
    }


    @PostMapping("/search")
    public String searchProduct(@RequestParam String nombre, Model model){
        log.info("Nombre del producto: {}", nombre);
        List<Products> products = productService.findAll().stream().filter( p -> p.getNombreProducto().contains(nombre)).collect(Collectors.toList());
        model.addAttribute("products", products);
        return "usuario/home";
    }
}

