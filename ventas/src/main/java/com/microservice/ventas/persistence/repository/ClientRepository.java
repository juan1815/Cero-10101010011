package com.microservice.ventas.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ventas.persistence.entities.Client;
import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
    /* query para buscar por telefono */
    List<Client> findByTelefono(String telefono); 
}
