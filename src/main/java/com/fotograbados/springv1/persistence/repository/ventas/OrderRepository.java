package com.fotograbados.springv1.persistence.repository.ventas;

import com.fotograbados.springv1.domain.dto.*;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {
    List<OrderEntity> findByUsers(Users users);
        //Ventas por producto
    @Query(nativeQuery = true,
            value = "SELECT YEAR(o.fecha_venta) AS year, MONTH(o.fecha_venta) AS month, p.id_producto AS product_id, SUM(op.cantidad) AS quantity_sold " +
                    "FROM order_entity o " +
                    "JOIN order_product op ON o.id_purchase = op.order_id " +
                    "JOIN products p ON op.product_id = p.id_producto " +
                    "GROUP BY YEAR(o.fecha_venta), MONTH(o.fecha_venta), p.id_producto " +
                    "ORDER BY year, month, product_id;")
    List<ProductSalesPerMonthDTO> findProductSalesPerMonth();

    //Sales Month
    @Query(nativeQuery = true,
            value = "SELECT YEAR(o.fecha_venta) AS year, MONTH(o.fecha_venta) AS month, SUM(o.total) AS total_ventas " +
                    "FROM order_entity o " +
                    "GROUP BY YEAR(o.fecha_venta), MONTH(o.fecha_venta) " +
                    "ORDER BY year, month;")
    List<TotalSalesPerMonthDTO> findTotalSalesPerMonth();

    @Query(nativeQuery = true,
            value = "SELECT YEAR(o.fecha_venta) AS year, MONTH(o.fecha_venta) AS month, DAY(o.fecha_venta) AS day, SUM(o.total) AS total_ventas " +
                    "FROM order_entity o " +
                    "GROUP BY YEAR(o.fecha_venta), MONTH(o.fecha_venta), DAY(o.fecha_venta) " +
                    "ORDER BY year, month, day;")
    List<TotalSalesPerDayDTO> findTotalSalesPerDay();

            //Sales for Region
    @Query(nativeQuery = true,
            value = "SELECT r.region_name AS region_name, u.tipo AS customer_type, u.codigo_postal AS postal_code, COUNT(*) AS customer_count " +
                    "FROM users u " +
                    "JOIN regions r ON u.direccion LIKE CONCAT('%', r.region_name, '%') " +
                    "GROUP BY r.region_name, u.tipo, u.codigo_postal ")
    List<CustomerTypePerRegionWithPostalCodeDTO> findCustomerTypesPerRegionWithPostalCode();


}
