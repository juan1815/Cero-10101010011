package com.fotograbados.springv1.domain.service.ventas;

import com.fotograbados.springv1.persistence.entities.ventas.BillEntity;
import com.fotograbados.springv1.persistence.entities.ventas.OrderEntity;
import com.fotograbados.springv1.persistence.repository.ventas.BillRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BillServiceImpl{
    @Autowired
    private BillRepository billRepository;

    public List<BillEntity> findAll(){
        return billRepository.findAll();
    }

    // pending change order filtering
    public Optional<BillEntity> findById(Long idBill) {
        return billRepository.findById(idBill);
    }

    
    // pendiente calcular precio de venta total dentro del controller /ventas/factura/save
    public BillEntity saveOrUpdate(BillEntity bill) {
        return billRepository.save(bill);
    }

}
