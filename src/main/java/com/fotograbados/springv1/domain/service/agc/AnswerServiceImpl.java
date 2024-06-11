package com.fotograbados.springv1.domain.service.agc;


import com.fotograbados.springv1.persistence.entities.agc.AnswerSatisfactionSur;
import com.fotograbados.springv1.persistence.repository.agc.AnswerSurfeyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerServiceImpl  implements IAnswerService{

    @Autowired
    private AnswerSurfeyRepository surfeyRepository;

    @Override
    public AnswerSatisfactionSur save(AnswerSatisfactionSur satisfactionSur) {
        return surfeyRepository.save(satisfactionSur);
    }

    @Override
    public void delete(Long idAnswer) {
        surfeyRepository.deleteById(idAnswer);
    }

    @Override
    public List<AnswerSatisfactionSur> findAll() {
        return surfeyRepository.findAll();
    }
}
