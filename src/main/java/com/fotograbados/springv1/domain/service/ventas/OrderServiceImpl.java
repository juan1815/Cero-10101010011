package com.fotograbados.springv1.domain.service.ventas;

import com.fotograbados.springv1.domain.dto.VentaMensual;
import com.fotograbados.springv1.domain.dto.VentaProducto;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import com.fotograbados.springv1.persistence.repository.ventas.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements IOrderService{
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public List<OrderEntity> findAll() {
        return orderRepository.findAll();
    }

    @Override
    public Optional<OrderEntity> findById(Long idOrder) {
        return orderRepository.findById(idOrder);
    }

    @Override
    public OrderEntity save(OrderEntity order) {
        return orderRepository.save(order);
    }

    @Override
    public String generarNumeroOrden() {
        int numero=0;
        String numeroConcatenado="";

        List<OrderEntity> orderEntities = findAll();

        List<Integer> numeros= new ArrayList<Integer>();

        orderEntities.forEach(o -> numeros.add( Integer.parseInt( o.getNumero())));

        if (orderEntities.isEmpty()) {
            numero=1;
        }else {
            numero= numeros.stream().max(Integer::compare).get();
            numero++;
        }

        if (numero<10) { //0000001000
            numeroConcatenado="000000000"+String.valueOf(numero);
        }else if(numero<100) {
            numeroConcatenado="00000000"+String.valueOf(numero);
        }else if(numero<1000) {
            numeroConcatenado="0000000"+String.valueOf(numero);
        }else if(numero<10000) {
            numeroConcatenado="0000000"+String.valueOf(numero);
        }

        return numeroConcatenado;
    }

    @Override
    public List<OrderEntity> findByUsers(Users users) {
        return orderRepository.findByUsers(users);
    }
    @Override
    public List<VentaMensual> obtenerVentasMensuales() {
        List<OrderEntity> orders = orderRepository.findAll();
        // Lógica para agrupar las ventas por mes y calcular el total
        return orders.stream()
                .collect(Collectors.groupingBy(order -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM");
                    return order.getFechaVenta().toInstant().atZone(ZoneId.systemDefault()).format(formatter);
                }, Collectors.summingDouble(OrderEntity::getTotal)))
                .entrySet().stream()
                .map(entry -> new VentaMensual(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }

    @Override
    public List<VentaProducto> obtenerVentasPorProducto() {
        List<OrderEntity> orders = orderRepository.findAll();
        // Lógica para agrupar las ventas por producto y calcular el total
        return orders.stream()
                .flatMap(order -> order.getBill().stream())
                .collect(Collectors.groupingBy(bill -> bill.getProducto().getNombreProducto(), Collectors.summingDouble(bill -> bill.getCantidad() * bill.getCostoUnidad())))
                .entrySet().stream()
                .map(entry -> new VentaProducto(entry.getKey(), entry.getValue()))
                .collect(Collectors.toList());
    }
}
