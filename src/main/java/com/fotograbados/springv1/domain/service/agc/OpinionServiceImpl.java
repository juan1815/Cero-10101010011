package com.fotograbados.springv1.domain.service.agc;

import com.fotograbados.springv1.persistence.entities.agc.OpinionProduct;
import com.fotograbados.springv1.persistence.repository.agc.OpinionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OpinionServiceImpl implements IOpinionService{
    @Autowired
    private OpinionRepository opinionRepository;
    @Override
    public OpinionProduct save(OpinionProduct opinionProduct) {
        return opinionRepository.save(opinionProduct);
    }

    @Override
    public List<OpinionProduct> findAll() {
        return opinionRepository.findAll();
    }
    @Override
    public void delete(Long idOpinion) {
        opinionRepository.deleteById(idOpinion);
    }
}
