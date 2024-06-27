package com.fotograbados.springv1.domain.service.agc;



import com.fotograbados.springv1.persistence.entities.agc.AnswerSatisfactionSur;

import java.util.List;
import java.util.Optional;

public interface IAnswerService {
    public AnswerSatisfactionSur save(AnswerSatisfactionSur satisfactionSur);
    public  void delete(Long idAnswer);
    public List<AnswerSatisfactionSur> findAll();
    public Long count();
   }
