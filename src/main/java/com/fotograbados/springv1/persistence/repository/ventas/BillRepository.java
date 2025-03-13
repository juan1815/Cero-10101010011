package com.fotograbados.springv1.persistence.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fotograbados.springv1.persistence.entities.ventas.BillEntity;

//only for querries db
@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long>{
    
}
