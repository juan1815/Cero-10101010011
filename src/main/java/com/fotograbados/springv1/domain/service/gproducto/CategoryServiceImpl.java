package com.fotograbados.springv1.domain.service.gproducto;

import com.fotograbados.springv1.persistence.entities.inventario.Category;
import com.fotograbados.springv1.persistence.repository.gproducto.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class CategoryServiceImpl implements ICategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Optional<Category> get(Long idCategoria) {
        return categoryRepository.findById(idCategoria);
    }

    @Override
    public void update(Category category) {
        categoryRepository.save(category);
    }

    @Override
    public void delete(Long idCategoria) {
        categoryRepository.deleteById(idCategoria);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
