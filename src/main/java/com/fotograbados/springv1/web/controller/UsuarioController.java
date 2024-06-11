package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.IUsuarioService;
//    import com.fotograbados.springv1.persistence.entities.RolEntity;
import com.fotograbados.springv1.persistence.entities.Users;
import jakarta.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;

    //BCryptPasswordEncoder passEncode= new BCryptPasswordEncoder();
    //@GetMapping("/registro")
    //public String create() {
      //  return "Login/registro";
    //}

    
    @GetMapping("/login")
    public String login() {
        return "usuario/log-in";
    }

    @PostMapping("/save")
    public String save(Users usuario) {
        logger.info("Usuario registro: {}", usuario);

      //  usuario.setPassword(passEncode.encode(usuario.getPassword()));
        usuarioService.save(usuario);

        return "redirect:/usuario/login";
    }

    @PostMapping("/updateRole")
    public String updateRole(@RequestParam Long userId, @RequestParam String newRole) {
        usuarioService.updateRole(userId, newRole);
        return "redirect:/admin/usuarios";
    }

//    @GetMapping("/acceder")
//    public String acceder(Users users, HttpSession session) {
//        logger.info("Accesos : {}", users);
//
//         //Buscar el usuario por email
//        Optional<Users> userOptional = usuarioService.findByEmail(users.getEmail());
//
//        if (userOptional.isPresent()) {
//            Users user = userOptional.get();
//            if (passEncode.matches(users.getPassword(), user.getPassword())) {
//                session.setAttribute("idusuario", user.getId());
//
//                String userType = user.getRolEntity().getTipo();
//                if ("ADMIN".equals(userType)) {
//                    return "redirect:/administrador";
//                } else if ("OPERADOR".equals(userType)) {
//                    return "redirect:/operador";
//                } else {
//                    return "redirect:/";
//                }
//            } else {
//                logger.info("Contraseña incorrecta");
//            }
//        } else {
//            logger.info("Usuario no existe");
//        }
//
//        return "redirect:/login";
//    }

    //CLOSE SESSION
    @GetMapping("/cerrar")
    public String cerrarSesion( HttpSession session ) {
        session.removeAttribute("idusuario");
        return "redirect:/home";
    }
}
