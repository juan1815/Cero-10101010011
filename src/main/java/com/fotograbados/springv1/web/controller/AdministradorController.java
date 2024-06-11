package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.gproducto.IProductService;
import com.fotograbados.springv1.domain.service.ventas.IOrderService;
import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/administrador")
public class AdministradorController {

    @Autowired
    private IProductService productService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IOrderService orderService;
    private final Logger logg = LoggerFactory.getLogger(AdministradorController.class);

    @GetMapping("")
    public String home(Model model) {
        List<Products> products = productService.findAll();
        model.addAttribute("productos", products);
        return "administrador/home";
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
    @GetMapping("/detalle/{id}")
    public String detalle(Model model, @PathVariable Long idOrder) {
        logg.info("Id de la orden {}",idOrder);
        OrderEntity order= orderService.findById(idOrder).get();

        model.addAttribute("detalles", order.getBill());

        return "administrador/detalleorden";
    }
}
