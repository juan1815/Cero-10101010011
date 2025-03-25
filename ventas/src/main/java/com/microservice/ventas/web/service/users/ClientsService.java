package com.microservice.ventas.web.service.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ventas.persistence.entities.Client;
import com.microservice.ventas.persistence.entities.ventas.OrderEntity;
import com.microservice.ventas.persistence.repository.ClientRepository;

@Service
public class ClientsService {

    @Autowired
    ClientRepository repositoryClient;
    //listar genreral
    public List<Client> getClientsListAll(){
        return (List<Client>) repositoryClient.findAll();
    }
    //listar unidad
    public Optional<Client> findClientById(Long id){
        return (Optional<Client>) repositoryClient.findById(id);
    }
    //guardar o actualizar
    public Boolean saveOrUpdate(Client c){
        Client c2 = repositoryClient.save(c);
        if(c2 != null){
            return true;
        } else{
            return false;
        }
    }
    //delete (editar acceso para permisos)
    public void deleteClient(Client c){
        repositoryClient.delete(c);
    }
}
