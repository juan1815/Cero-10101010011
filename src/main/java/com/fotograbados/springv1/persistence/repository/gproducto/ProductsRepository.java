package com.fotograbados.springv1.persistence.repository.gproducto;

import com.fotograbados.springv1.persistence.entities.inventario.Products;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductsRepository extends JpaRepository<Products, Long> {
}
