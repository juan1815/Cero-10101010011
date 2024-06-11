package com.fotograbados.springv1.domain.service.ventas;

import com.fotograbados.springv1.persistence.entities.ventas.ShippingDetail;
import com.fotograbados.springv1.persistence.repository.ventas.ShippingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShippingServiceImpl implements IShippingService {
   @Autowired
   private ShippingRepository shippingRepository;

    @Override
    public ShippingDetail save(ShippingDetail shippingDetail) {
        return shippingRepository.save(shippingDetail);
    }
}
