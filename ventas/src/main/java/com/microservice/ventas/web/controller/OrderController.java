package com.microservice.ventas.web.controller;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.microservice.ventas.persistence.entities.Users;
import com.microservice.ventas.persistence.entities.ventas.OrderEntity;
import com.microservice.ventas.web.service.ventas.OrderService;

import lombok.Delegate;

@RestController
public class OrderController {
    
    @Autowired
    OrderService serviceOrder;

    /**
     * metodos a devolver en.. VENTAS- pedido *
     * -- listar por unidad
     * -- litado general
     * -- registrar
     * -- actulizar unicamente fecha de envio
     * -- eliminar
     */

    @GetMapping("/orders")
    public List<OrderEntity> consultaGeneral(Model model) {
        List<OrderEntity> allOrders = serviceOrder.getOrderListAll();
        return allOrders;
    }

    @GetMapping("/orders/{id}")
    public Optional<OrderEntity> uniqueOrder(@PathVariable Long id){
        Optional<OrderEntity> anyOrderUnique = serviceOrder.getOrderById(id);
        return anyOrderUnique;
    }

    @PostMapping("/save/order")
    public Boolean saveOrder(@RequestBody OrderEntity o){
        return serviceOrder.save(o);
    }

    @PutMapping("/putOrder/{id}")
    public Boolean putOrder(@RequestBody Date d_current, @PathVariable Long id){
        return serviceOrder.editF_envio(d_current, id);
    }

    @DeleteMapping("/delete/order")
    public void deleteOrder(@RequestBody OrderEntity o){
        serviceOrder.deleteOrder(o);
    }

}
