package com.fotograbados.springv1.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fotograbados.springv1.persistence.entities.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Long>{
    
}
