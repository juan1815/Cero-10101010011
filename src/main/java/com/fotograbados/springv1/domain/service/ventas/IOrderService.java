package com.fotograbados.springv1.domain.service.ventas;



import com.fotograbados.springv1.domain.dto.ChartData;
import com.fotograbados.springv1.domain.dto.VentaMensual;
import com.fotograbados.springv1.domain.dto.VentaProducto;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;

import java.util.List;
import java.util.Optional;

public interface IOrderService {
    List<OrderEntity> findAll();
    Optional<OrderEntity> findById(Long idOrder);
    OrderEntity save(OrderEntity order);
    String generarNumeroOrden();
    List<OrderEntity> findByUsers(Users users);
    List<VentaMensual> obtenerVentasMensuales();
    List<VentaProducto> obtenerVentasPorProducto();
    Long count();
    List<ChartData> getSalesDataByMonth();
}
