package com.fotograbados.springv1.persistence.repository.agc;

import com.fotograbados.springv1.persistence.entities.agc.AnswerSatisfactionSur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerSurfeyRepository extends JpaRepository<AnswerSatisfactionSur, Long> {
}
