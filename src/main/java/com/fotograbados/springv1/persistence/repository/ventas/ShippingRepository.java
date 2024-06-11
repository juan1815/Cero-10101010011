package com.fotograbados.springv1.persistence.repository.ventas;

import com.fotograbados.springv1.persistence.entities.ventas.ShippingDetail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShippingRepository extends JpaRepository<ShippingDetail, Long> {
}
