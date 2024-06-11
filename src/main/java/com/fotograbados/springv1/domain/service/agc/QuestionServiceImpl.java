package com.fotograbados.springv1.domain.service.agc;

import com.fotograbados.springv1.persistence.entities.agc.QuestionSatisfactionSur;
import com.fotograbados.springv1.persistence.repository.agc.QuestionSurfeyRepository;
import com.fotograbados.springv1.domain.service.agc.IQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuestionServiceImpl implements IQuestionService {

    @Autowired
    private QuestionSurfeyRepository surfeyRepository;

    @Override
    public QuestionSatisfactionSur save(QuestionSatisfactionSur sur) {
        return surfeyRepository.save(sur);
    }

    @Override
    public Optional<QuestionSatisfactionSur> get(Long idPregunta) {
        return surfeyRepository.findById(idPregunta);
    }

    @Override
    public void update(QuestionSatisfactionSur sur) {
        surfeyRepository.save(sur);
    }

    @Override
    public void delete(Long idPregunta) {
        surfeyRepository.deleteById(idPregunta);
    }

    @Override
    public List<QuestionSatisfactionSur> findAll() {
        return surfeyRepository.findAll();
    }
}
