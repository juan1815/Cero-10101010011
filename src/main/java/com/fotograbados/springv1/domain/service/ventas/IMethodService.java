package com.fotograbados.springv1.domain.service.ventas;


import com.fotograbados.springv1.persistence.entities.ventas.PaymentMethodEntity;

public interface IMethodService {
    public PaymentMethodEntity save(PaymentMethodEntity paymentMethod);
}
