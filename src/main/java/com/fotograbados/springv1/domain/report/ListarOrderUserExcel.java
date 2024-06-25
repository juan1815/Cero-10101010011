package com.fotograbados.springv1.domain.report;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;

import java.util.List;

public class ListarOrderUserExcel {
    private List<OrderEntity> orderEntities;

    public ListarOrderUserExcel(List<OrderEntity> orderEntities) {this.orderEntities = orderEntities;}

    public List<OrderEntity> getOrderEntities() {return orderEntities;}

    public void SetOrderEntities(List<OrderEntity> orderEntities){this.orderEntities = orderEntities;}
}
