package com.fotograbados.springv1.domain.service.gproducto;

import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.persistence.repository.gproducto.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductsRepository productsRepository;

    @Override
    public Products save(Products products) {
        return productsRepository.save(products);
    }

    @Override
    public Optional<Products> get(Long idProducto) {
        return productsRepository.findById(idProducto);
    }

    @Override
    public void update(Products products) {
        productsRepository.save(products);
    }

    @Override
    public void delete(Long idProducto) {
        productsRepository.deleteById(idProducto);
    }

    @Override
    public List<Products> findAll() {
        return productsRepository.findAll();
    }
}
