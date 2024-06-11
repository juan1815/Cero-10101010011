package com.fotograbados.springv1.persistence.repository.ventas;

import com.fotograbados.springv1.domain.dto.VentaDTO;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUsers(Users users);

    @Query(nativeQuery = true, value = "SELECT " +
            "o.fecha_venta AS fechaVenta, " +
            "u.nombre AS nombreUsuario, " +
            "o.numero AS numeroOrden, " +
            "s.descripcion AS descripcionEnvio, " +
            "o.total AS totalVenta, " +
            "p.nombre AS metodoPago " +
            "FROM " +
            "order_entity o " +
            "JOIN users u ON o.id_usuario = u.id " +
            "JOIN shipping_detail s ON o.id_detalleenv = s.id_detalleenv " +
            "JOIN payment_method_entity p ON o.id_metodo_pago = p.id_metodo_pago " +
            "WHERE o.estado = 'Completado' " +
            "ORDER BY o.fecha_venta DESC")
    List<VentaDTO> generarReporteVentas();

    @Query("SELECT YEAR(o.fechaVenta) AS year, MONTH(o.fechaVenta) AS month, SUM(o.total) AS totalVentas " +
            "FROM OrderEntity o " +
            "WHERE o.estado = 'Completado' " +
            "GROUP BY YEAR(o.fechaVenta), MONTH(o.fechaVenta) " +
            "ORDER BY YEAR(o.fechaVenta) DESC, MONTH(o.fechaVenta) DESC")
    List<Object[]> generarReporteVentasMensuales();

    @Query("SELECT DATE(o.fechaVenta) AS fechaVenta, SUM(o.total) AS totalVentas " +
            "FROM OrderEntity o " +
            "WHERE o.estado = 'Completado' " +
            "GROUP BY DATE(o.fechaVenta) " +
            "ORDER BY DATE(o.fechaVenta) DESC")
    List<Object[]> generarReporteVentasDiarias();

}
