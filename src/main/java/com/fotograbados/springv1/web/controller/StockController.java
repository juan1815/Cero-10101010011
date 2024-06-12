package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.gproducto.IStockService;
import com.fotograbados.springv1.persistence.entities.inventario.StockMatPri;
import com.fotograbados.springv1.web.server.NotFoundException;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequestMapping("/stock")
public class StockController {
    private final Logger LOGGER = LoggerFactory.getLogger(StockController.class);

    @Autowired
    private IStockService stockService;

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("")
    public String showstock(Model model) {
        model.addAttribute("stock", stockService.findAll());
        return "stock/showstock";
    }

    @GetMapping("/createStock")
    public String createstock() {
        return "stock/creatstock";
    }

    @PostMapping("/saveStock")
    public String saveStock(StockMatPri matPri){
        LOGGER.info("Este es el objeto Stock {}", matPri);

        stockService.save(matPri);
        return "redirect:/stock";
    }
    @GetMapping("/edit/{idStockMat}")
    public String editStock(@PathVariable Long idStockMat, Model model) {
        StockMatPri stock = new StockMatPri();
        Optional<StockMatPri> optionalStockMatPri = stockService.get(idStockMat);
        stock = optionalStockMatPri.get();

        LOGGER.info("stock buscado: {}", stock);
        model.addAttribute("stock", stock);
        return "stock/edistock";
    }

    @PostMapping("/updateStock")
    public String updateStock(StockMatPri stock) {
        StockMatPri st = new StockMatPri();
        st = stockService.get(stock.getIdStockMat()).get();

        stockService.update(stock);
        return "redirect:/stock";
    }
    @GetMapping("/deleteStock/{idStockMat}")
    public String deleteStock(@PathVariable Long idStockMat){
        StockMatPri s = new StockMatPri();
        s = stockService.get(idStockMat).get();

        stockService.delete(idStockMat);
        return "redirect:/stock";
    }
}
