package com.microservice.ventas.persistence.repository.ventas;


import com.microservice.ventas.persistence.entities.ventas.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
//only for qurries db
@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    // List<OrderEntity> findByUsers(Users users);
    //Sales To graphic
    // @Query(nativeQuery = true, value = "SELECT MONTH(fecha_venta) AS month, COUNT(*) AS sales_count FROM pedido  GROUP BY MONTH(fecha_venta)")
    // List<Object[]> countSalesByMonth();  --13/3/2025 it'll not use currently

    //edit
    @Query(nativeQuery = true, value="UPDATE fg_db_ventas.pedido SET fecha_envio = :f_envio WHERE (id_pedido = :id)")
    List<OrderEntity> updateTransportationSent(@Param("fecha_envio") Date f_envio, @Param("id_pedido") Long id);
}
