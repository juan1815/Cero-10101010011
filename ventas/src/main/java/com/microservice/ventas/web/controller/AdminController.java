package com.microservice.ventas.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ventas.persistence.entities.Client;
import com.microservice.ventas.persistence.entities.Users;
import com.microservice.ventas.persistence.repository.ClientRepository;
import com.microservice.ventas.persistence.repository.UserRepository;
import com.microservice.ventas.web.service.users.ClientsService;
import com.microservice.ventas.web.service.users.UsersService;

@RestController
public class AdminController {
    
    @Autowired
    UsersService serviceUsers;

    /** metodos a devolver
     * -- listar usuarios generales
     * -- por un unico campo (generalizar)
     * -- registrar o actualizar
     * -- eliminar (permisos requeridos admin)
    */

    @GetMapping("/users")
    public List<Users> consultaGeneral(Model model) {
        List<Users> anyUsers = serviceUsers.getUsersListAll();
        return anyUsers;
    }

    @GetMapping("/users/{id}")
    public Optional<Users> uniqueUser(@PathVariable Long id){
        Optional<Users> anyUserUnique = serviceUsers.findUserById(id);
        return anyUserUnique;
    }

    @PostMapping("/save/user")
    public Boolean saveUsers(@RequestBody Users u){
        return serviceUsers.saveOrUpdate(u);
    }

    @DeleteMapping("/delete/user")
    public Boolean deleteUser(@RequestBody Users u){
        if(u!=null){
            return true;
        } else{
            return false;
        }
    }

}
