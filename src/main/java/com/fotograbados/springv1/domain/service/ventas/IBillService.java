package com.fotograbados.springv1.domain.service.ventas;

import com.fotograbados.springv1.persistence.entities.inventario.DetailProduct;
import com.fotograbados.springv1.persistence.entities.ventas.BillEntity;

public interface IBillService {
    BillEntity save(BillEntity bill);
}
