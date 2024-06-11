package com.fotograbados.springv1.persistence.repository.ventas;

import com.fotograbados.springv1.persistence.entities.ventas.BillEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillRepository extends JpaRepository<BillEntity, Long> {
}
