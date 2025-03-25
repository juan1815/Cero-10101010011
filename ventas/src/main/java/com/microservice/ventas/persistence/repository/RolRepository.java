package com.microservice.ventas.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.microservice.ventas.persistence.entities.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long>{
    
}
