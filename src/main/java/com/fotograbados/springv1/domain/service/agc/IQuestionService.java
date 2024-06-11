package com.fotograbados.springv1.domain.service.agc;

import com.fotograbados.springv1.persistence.entities.agc.QuestionSatisfactionSur;


import java.util.List;
import java.util.Optional;

public interface IQuestionService {
    QuestionSatisfactionSur save(QuestionSatisfactionSur sur);
    Optional<QuestionSatisfactionSur> get(Long idPregunta);
    void update(QuestionSatisfactionSur sur);
     void delete(Long idPregunta);
    List<QuestionSatisfactionSur> findAll();

}
