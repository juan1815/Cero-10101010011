package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.ventas.IOrderService;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private IOrderService orderService;
    @GetMapping("/compras")
    public String obtenerCompras(Model model, HttpSession session) {
        //model.addAttribute("sesion", session.getAttribute("idusuario"));

        //Users users= usuarioService.findById(Long.parseLong(session.getAttribute("idusuario").toString()) ).get();
        //List<OrderEntity> ordenes= orderService.findByUsers(users);
        //logger.info("ordenes {}", ordenes);

       // model.addAttribute("ordenes", ordenes);

        return "usuario/compras";
    }

    @GetMapping("/detalle/{idOrder}")
    public String detalleCompra(@PathVariable Long idOrder, HttpSession session, Model model) {
        logger.info("Id de la orden: {}", idOrder);
        Optional<OrderEntity> order=orderService.findById(idOrder);

        model.addAttribute("detalles", order.get().getBill());


        //session
       // model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/detallecompra";
    }
    //END PURCHASE



}
