package com.fotograbados.springv1.domain.report;

import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import org.aspectj.weaver.ast.Or;

import java.util.List;

public class ListOrderUserPdf {
    private List<OrderEntity> orderEntities;

    public ListOrderUserPdf(List<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }

    public List<OrderEntity> getOrderEntities() {
        return orderEntities;
    }

    public void setOrderEntities(List<OrderEntity> orderEntities) {
        this.orderEntities = orderEntities;
    }
}
