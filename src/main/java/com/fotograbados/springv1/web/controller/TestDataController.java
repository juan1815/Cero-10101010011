package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import com.fotograbados.springv1.persistence.repository.ventas.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
public class TestDataController {

    @Autowired
    private OrderRepository orderRepository;

    @GetMapping("/generateTestData")
    public String generateTestData() {
        // Generar algunos datos de prueba para OrderEntity
        OrderEntity order1 = new OrderEntity();
        order1.setNumero("ORD001");
        order1.setFechaVenta(getDate(2023, Calendar.JANUARY, 15)); // 15 de Enero de 2023
        order1.setFechaEnvio(getDate(2023, Calendar.JANUARY, 20)); // 20 de Enero de 2023
        order1.setTotal(1500.0); // Total de la venta
        order1.setDescuento(100.0); // Descuento
        order1.setEstado("Procesado"); // Estado de la orden

        OrderEntity order2 = new OrderEntity();
        order2.setNumero("ORD002");
        order2.setFechaVenta(getDate(2023, Calendar.FEBRUARY, 10)); // 10 de Febrero de 2023
        order2.setFechaEnvio(getDate(2023, Calendar.FEBRUARY, 15)); // 15 de Febrero de 2023
        order2.setTotal(2000.0); // Total de la venta
        order2.setDescuento(150.0); // Descuento
        order2.setEstado("Enviado"); // Estado de la orden

        OrderEntity order3 = new OrderEntity();
        order3.setNumero("ORD003");
        order3.setFechaVenta(getDate(2023, Calendar.MARCH, 5)); // 5 de Marzo de 2023
        order3.setFechaEnvio(getDate(2023, Calendar.MARCH, 10)); // 10 de Marzo de 2023
        order3.setTotal(1800.0); // Total de la venta
        order3.setDescuento(120.0); // Descuento
        order3.setEstado("Entregado"); // Estado de la orden

        // Guardar las órdenes en el repositorio
        orderRepository.save(order1);
        orderRepository.save(order2);
        orderRepository.save(order3);

        return "Datos de prueba generados exitosamente";
    }

    // Método auxiliar para obtener una fecha específica
    private Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day, 0, 0, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }
}