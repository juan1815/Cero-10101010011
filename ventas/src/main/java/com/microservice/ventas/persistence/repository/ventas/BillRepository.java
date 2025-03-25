package com.microservice.ventas.persistence.repository.ventas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.ventas.persistence.entities.ventas.BillEntity;


//only for querries db
@Repository
public interface BillRepository extends JpaRepository<BillEntity, Long>{
    
}
// C:\Users\LEIDY\Desktop\ventas\src\main\java\com\fotograbados\ventas\persistence\repository\ventas\BillRepository.java