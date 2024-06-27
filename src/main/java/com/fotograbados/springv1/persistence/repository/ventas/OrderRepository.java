package com.fotograbados.springv1.persistence.repository.ventas;

import com.fotograbados.springv1.domain.dto.*;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUsers(Users users);
    //Sales To graphic
    @Query(nativeQuery = true, value = "SELECT MONTH(fecha_venta) AS month, COUNT(*) AS sales_count FROM pedido  GROUP BY MONTH(fecha_venta)")
    List<Object[]> countSalesByMonth();



}
