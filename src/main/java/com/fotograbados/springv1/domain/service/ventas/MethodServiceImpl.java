package com.fotograbados.springv1.domain.service.ventas;


import com.fotograbados.springv1.persistence.entities.ventas.PaymentMethodEntity;
import com.fotograbados.springv1.persistence.repository.ventas.MethodRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MethodServiceImpl implements IMethodService{

    @Autowired
    private MethodRepository methodRepository;
    @Override
    public PaymentMethodEntity save(PaymentMethodEntity paymentMethod) {
        return methodRepository.save(paymentMethod);
    }
}
