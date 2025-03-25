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

import com.microservice.ventas.persistence.entities.Client;
import com.microservice.ventas.persistence.repository.ClientRepository;
import com.microservice.ventas.web.service.users.ClientsService;

public class ClientController {
    @Autowired
    ClientRepository respositoryClient;

    @Autowired
    ClientsService serviceClients;

    /** metodos a devolver
     * -- listar clientes generales
     * -- por un unico campo (generalizar)
     * -- registrar o actualizar
     * -- eliminar (permisos requeridos admin)
    */
    
    @GetMapping("/clients")
    public List<Client> consulta(Model model) {
        List<Client> anyClient = respositoryClient.findAll();
        return anyClient;
    }

    @GetMapping("/clients/{id}")
    public Optional<Client> uniqueClient(@PathVariable Long id){
        Optional<Client> anyClientUnique = serviceClients.findClientById(id);
        return anyClientUnique;
    }

    @PostMapping("/save/client")
    public Boolean saveClient(@RequestBody Client c){
        return serviceClients.saveOrUpdate(c);
    }

    @DeleteMapping("/delete/client")
    public Boolean deleteClient(@RequestBody Client c){
        if(c!=null){
            return true;
        } else{
            return false;
        }
    }
}
