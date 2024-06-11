package com.fotograbados.springv1.domain.service.ventas;

import com.fotograbados.springv1.persistence.entities.ventas.BillEntity;
import com.fotograbados.springv1.persistence.repository.ventas.IBillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl implements IBillService{
    @Autowired
    private IBillRepository billRepository;

    @Override
    public BillEntity save(BillEntity bill) {
        return billRepository.save(bill);
    }
}
