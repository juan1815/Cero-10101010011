package com.microservice.ventas.web.service.users;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.microservice.ventas.persistence.entities.Users;
import com.microservice.ventas.persistence.repository.ClientRepository;
import com.microservice.ventas.persistence.repository.UserRepository;

@Service
public class UsersService {

    @Autowired
    UserRepository repositoryUser;

    // listar genreral
    public List<Users> getUsersListAll() {
        return (List<Users>) repositoryUser.findAll();
    }

    // listar unidad
    public Optional<Users> findUserById(Long id) {
        return (Optional<Users>) repositoryUser.findById(id);
    }

    // guardar o actualizar
    public Boolean saveOrUpdate(Users u) {
        Users u2= repositoryUser.save(u);
        if (u2 != null) {
            return true;
        } else {
            return false;
        }
    }

    // delete (editar acceso para permisos)
    public void deleteClient(Users u) {
        repositoryUser.delete(u);
    }
}