package com.fotograbados.springv1.domain.service.gproducto;

import com.fotograbados.springv1.persistence.entities.inventario.StockMatPri;
import com.fotograbados.springv1.persistence.repository.gproducto.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class StockServiceImpl implements IStockService{
    @Autowired
    private StockRepository stockRepository;

    @Override
    public StockMatPri save(StockMatPri stockMatPri) {
        return stockRepository.save(stockMatPri);
    }

    @Override
    public Optional<StockMatPri> get(Long idStockMath) {
        return stockRepository.findById(idStockMath);
    }

    @Override
    public void update(StockMatPri stockMatPri) {
        stockRepository.save(stockMatPri);
    }

    @Override
    public void delete(Long idStockMath) {
        stockRepository.deleteById(idStockMath);
    }

    @Override
    public List<StockMatPri> findAll() {
        return stockRepository.findAll();
    }
}
