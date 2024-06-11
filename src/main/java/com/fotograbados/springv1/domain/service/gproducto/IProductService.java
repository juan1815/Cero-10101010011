package com.fotograbados.springv1.domain.service.gproducto;

import com.fotograbados.springv1.persistence.entities.inventario.Products;


import java.util.List;
import java.util.Optional;

public interface IProductService {
    public Products save(Products products);
    Optional<Products> get(Long idProducto);
    public void update(Products products);
    public void delete(Long idProducto);
    public List<Products> findAll();

}
