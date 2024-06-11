package com.fotograbados.springv1.web.controller;

import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.gproducto.ICategoryService;
import com.fotograbados.springv1.persistence.entities.inventario.Category;
import com.fotograbados.springv1.web.server.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/category")
public class CategoryController {
    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private IUsuarioService usuarioService;

    //CATEGORIES
    @GetMapping("/showcat")
    public String showCat(Model model){
        model.addAttribute("category", categoryService.findAll());
        return "categoria/showcat";
    }
    @GetMapping("/categoria")
    public String createCategoria(){
        return "categoria/createcat";
    }

    @PostMapping("/saveCat")
    public String saveCat(Category category, HttpSession session)
            {
        LOGGER.info("Este es el objeto producto {}", category);

        // to save the product with an actor on the Proyect of Software
//        Users u = usuarioService.findById(Long.parseLong(session.getAttribute("idUsuario").toString())).get();
//        category.setUsuario(u);

        categoryService.save(category);
        return "redirect:/category/showcat";
    }

    @GetMapping("/editCat/{idCategoria}")
    public String edit(@PathVariable Long idCategoria, Model model) {
        Optional<Category> optionalCategory = categoryService.get(idCategoria);
        if (optionalCategory.isPresent()) {
            model.addAttribute("category", optionalCategory.get());
            return "categoria/editcat";
        } else {
            throw new NotFoundException("Categoria no encontrada");
        }
    }

    @PostMapping("/updateCat")
    public String update(Category category) {
        Category c = categoryService.get(category.getIdCategoria()).orElseThrow(() -> new NotFoundException("Categoria no encontrada"));
        // category.setUsuario(c.getUsuario());
        categoryService.update(category);
        return "redirect:/category/showcat";
    }

    @GetMapping("/deleteCat/{idCategoria}")
    public String delete(@PathVariable Long idCategoria) {

        Category c = new Category();
        c = categoryService.get(idCategoria).get();

        categoryService.delete(idCategoria);
        return "redirect:/category/showcat";
    }

}
