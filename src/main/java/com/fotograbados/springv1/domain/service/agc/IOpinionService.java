package com.fotograbados.springv1.domain.service.agc;


import com.fotograbados.springv1.persistence.entities.agc.OpinionProduct;

import java.util.List;

public interface IOpinionService {
    public OpinionProduct save(OpinionProduct opinionProduct);
    public List<OpinionProduct> findAll();
    public void delete(Long idOpinion);
}
