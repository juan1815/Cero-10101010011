package com.fotograbados.springv1.domain.service.gproducto;

import com.fotograbados.springv1.persistence.entities.inventario.Category;

import java.util.List;
import java.util.Optional;


public interface ICategoryService {
    public Category save(Category category);
    Optional<Category> get(Long idCategoria);

    public void update(Category category);
    public void delete(Long idCategoria);
    public List<Category> findAll();


}
