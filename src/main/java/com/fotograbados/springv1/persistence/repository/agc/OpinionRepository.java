package com.fotograbados.springv1.persistence.repository.agc;

import com.fotograbados.springv1.persistence.entities.agc.OpinionProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OpinionRepository extends JpaRepository<OpinionProduct, Long> {
}
