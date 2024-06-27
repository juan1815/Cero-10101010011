package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.dto.ChartData;
import com.fotograbados.springv1.domain.dto.VentaMensual;
import com.fotograbados.springv1.domain.dto.VentaProducto;
import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.agc.IAnswerService;
import com.fotograbados.springv1.domain.service.agc.IOpinionService;
import com.fotograbados.springv1.domain.service.gproducto.IProductService;
import com.fotograbados.springv1.domain.service.ventas.IOrderService;
import com.fotograbados.springv1.persistence.entities.RolEntity;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.*;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IOrderService orderService;
    @Autowired
    private IAnswerService answerService;
    @Autowired
    private IOpinionService opinionService;
    private final Logger logg = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping("")
    public String home(Model model) {
        Long ventasCount = orderService.count();
        Long resenasCount = answerService.count();
        model.addAttribute("ventasCount", ventasCount);
        model.addAttribute("resenasCount", resenasCount);

        List<ChartData> chartData = orderService.getSalesDataByMonth();
        model.addAttribute("chartData", chartData);

        return "administrador/dashboard";
    }
    @GetMapping("/usuarios")
    public String usuarios(Model model) {
        model.addAttribute("usuarios", usuarioService.findAll());
        return "administrador/usuarios";
    }

    @GetMapping("/ordenes")
    public String ordenes(Model model) {
        model.addAttribute("ordenes", orderService.findAll());
        return "administrador/ordenes";
    }
    @GetMapping("/info")
    public String info(Model model){
        RolEntity rol = new RolEntity();
        rol.setIdRol(1L);
        rol.setTipo("usuario");

        Users users = new Users();
        users.setNombre("Pablo Elvis Tek");
        users.setUsername("Pablo");
        users.setEmail("pablo@gmail.com");
        users.setAvatar("");
        users.setDireccion("Calle 41 # 34-21");
        users.setCodigoPostal("2343");
        users.setTelefono("32275581");
        users.setRolEntity(rol);

        model.addAttribute("usuario", users);
        return "administrador/info";
    }

    @GetMapping("/detalle/{idOrder}")
    public String detalle(Model model, @PathVariable Long idOrder) {
        logg.info("Id de la orden {}", idOrder);

        // Buscar la orden por ID
        Optional<OrderEntity> orderOptional = orderService.findById(idOrder);
        if (!orderOptional.isPresent()) {
            logg.error("Orden no encontrada con id: {}", idOrder);
            return "redirect:/errorPage"; // Redirigir a una página de error si la orden no se encuentra
        }

        OrderEntity order = orderOptional.get();
        logg.info("Detalles de la orden: {}", order.getBill());

        model.addAttribute("detalles", order.getBill());

        return "administrador/detalleorden";
    }
}
