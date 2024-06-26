package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.UploadFileService;
import com.fotograbados.springv1.domain.service.ventas.IOrderService;
import com.fotograbados.springv1.persistence.entities.RolEntity;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private UploadFileService uploadFileService;
    private final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
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
        LOGGER.info("Id de la orden: {}", idOrder);
        Optional<OrderEntity> order=orderService.findById(idOrder);

        model.addAttribute("detalles", order.get().getBill());


        //session
       // model.addAttribute("sesion", session.getAttribute("idusuario"));
        return "usuario/detallecompra";
    }
    //END PURCHASE
    //INFO
    @GetMapping("/info")
    public String info(Model model){
        RolEntity rol = new RolEntity();
        rol.setIdRol(1L);
        rol.setTipo("usuario");

        Users users = new Users();
        users.setNombre("Pablo Elvis Tek");
        users.setUsername("Pablo");
        users.setEmail("pablo@gmail.com");
        users.setAvatar("/custom-images/fotico.jpg");
        users.setDireccion("Calle 41 # 34-21");
        users.setCodigoPostal("2343");
        users.setTelefono("32275581");
        users.setRolEntity(rol);

        model.addAttribute("usuario", users);
        return "usuario/info";
    }

    @PostMapping("/saveInfo")
    public String saveInfo(@ModelAttribute("usuario") Users users,
                           @RequestParam("avatar") MultipartFile file) throws IOException {
        if (!file.isEmpty()) {
            String nombreImagen = uploadFileService.saveImage(file);
            users.setAvatar("/avatar/" + nombreImagen);
        } else if (users.getId() == null) {
            users.setAvatar("/images/default.jpg");
        }

        usuarioService.save(users);
        return "redirect:/cliente/info";
    }
    @PostMapping("/cambiar-foto")
    public String cambiarFotoPerfil(@RequestParam("avatar") MultipartFile file,
                                    @ModelAttribute("usuario") Users usuario) throws IOException {
        if (!file.isEmpty()) {
            String nombreImagen = uploadFileService.saveImage(file);
            usuario.setAvatar("/images/" + nombreImagen);
        }

        usuarioService.save(usuario);
        return "redirect:/home";
    }

}
