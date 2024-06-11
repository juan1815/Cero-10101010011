package com.fotograbados.springv1.domain.service.ventas;


import com.fotograbados.springv1.persistence.entities.ventas.ShippingDetail;

public interface IShippingService {
    public ShippingDetail save(ShippingDetail shippingDetail);
}
