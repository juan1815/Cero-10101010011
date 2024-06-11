package com.fotograbados.springv1.persistence.repository.gproducto;

import com.fotograbados.springv1.persistence.entities.inventario.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
