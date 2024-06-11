package com.fotograbados.springv1.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fotograbados.springv1.persistence.entities.RolEntity;

public interface RolRepository extends JpaRepository<RolEntity, Long>{
    RolEntity findByTipo(String tipo);
}
