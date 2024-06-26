package com.fotograbados.springv1.domain.service.gproducto;


import com.fotograbados.springv1.persistence.entities.inventario.StockMatPri;

import java.util.List;
import java.util.Optional;

public interface IStockService {
    public StockMatPri save(StockMatPri stockMatPri);
    Optional<StockMatPri> get(Long idStockMath);
    public void update(StockMatPri stockMatPri);
    public void delete(Long idStockMath);
    public List<StockMatPri> findAll();
    public void addStock(Long idStockMat, int cantidad);
}
