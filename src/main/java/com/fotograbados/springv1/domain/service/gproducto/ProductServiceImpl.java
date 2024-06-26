package com.fotograbados.springv1.domain.service.gproducto;

import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.persistence.entities.inventario.StockMatPri;
import com.fotograbados.springv1.persistence.repository.gproducto.ProductsRepository;
import com.fotograbados.springv1.persistence.repository.gproducto.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductServiceImpl implements IProductService {
    @Autowired
    private ProductsRepository productsRepository;
    @Autowired
    private StockRepository stockRepository;

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

    @Override
    public boolean buyProducts(Long idProducto) {
        Products product = productsRepository.findById(idProducto).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
        StockMatPri stock = product.getStock();

        if (stock.descontarCantidad()) {
            stockRepository.save(stock);
            return true;
        } else {
            return false; // No hay suficiente stock
        }
    }


}
