package com.fotograbados.springv1.persistence.repository.agc;

import com.fotograbados.springv1.persistence.entities.agc.QuestionSatisfactionSur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionSurfeyRepository extends JpaRepository<QuestionSatisfactionSur, Long> {
}
