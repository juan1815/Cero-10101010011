package com.fotograbados.springv1.web.controller;


import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.UploadFileService;
import com.fotograbados.springv1.domain.service.gproducto.ICategoryService;
import com.fotograbados.springv1.domain.service.gproducto.IProductService;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.inventario.Category;
import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.web.server.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/productos")
public class ProductoController {
    //Resources service
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private IProductService productService;
    @Autowired
    private IUsuarioService usuarioService;
    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private UploadFileService uploadFileService;

    //ANNOTATIONS
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("products", productService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create(Model model){
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);

        return "productos/create";
    }

    @PostMapping("/save")
    public String save( Products products,
                       @RequestParam("img") MultipartFile file,
                       @RequestParam("category.idCategoria") Long idCategoria,
                       HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto Producto {}", products);

        // Obtener el ID del usuario de la sesión
//        Object idUsuarioObj = session.getAttribute("idusuario");
//        if (idUsuarioObj == null) {
//            LOGGER.error("El atributo 'idusuario' en la sesión es null.");
//            return "redirect:/errorPage"; // O cualquier página de error apropiada
//        }
//
//        Long idUsuario = Long.parseLong(idUsuarioObj.toString());
//        Users u = usuarioService.findById(idUsuario).orElse(null);
//
//        if (u == null) {
//            LOGGER.error("Usuario no encontrado para id: {}", idUsuario);
//            return "redirect:/errorPage"; // O cualquier página de error apropiada
//        }
        Users users = usuarioService.findById( Long.parseLong(session.getAttribute("idUsuario").toString())).get();
        // Asignar el usuario al producto
        products.setUsuario(users);

        // Guardar la imagen y obtener el nombre
        if (products.getIdProducto() == null && !file.isEmpty()) {
            String nombreImagen = uploadFileService.saveImage(file);
            products.setImagen(nombreImagen);
        }

        // Validar y establecer la categoría y el stock del producto
        Category category = categoryService.get(idCategoria).orElse(null);
        if (category == null) {
            LOGGER.error("Categoría no encontrada para id: {}", idCategoria);
            return "redirect:/errorPage"; // Manejar el caso donde no se encuentra la categoría
        }
        products.setCategory(category);

        // Guardar el producto
        productService.save(products);
        return "redirect:/productos";
    }


    @GetMapping("/edit/{idProducto}")
    public String edit(@PathVariable Long idProducto, Model model) {
        // Obtener el producto por su ID
        Optional<Products> optionalProducts = productService.get(idProducto);
        Products products = optionalProducts.orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Obtener todas las categorías disponibles
        List<Category> categories = categoryService.findAll();

        LOGGER.info("Producto buscado: {}", products);

        // Agregar el producto y las categorías al modelo
        model.addAttribute("products", products);
        model.addAttribute("categories", categories);
        return "productos/edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("producto") Products products,@RequestParam("category.idCategoria") Long idCategoria, @RequestParam("img") MultipartFile file) throws IOException {
        Products p = productService.get(products.getIdProducto()).orElseThrow(() -> new NotFoundException("Producto no encontrado"));

        if (file.isEmpty()) { // Si no se selecciona un nuevo archivo de imagen, mantenemos la imagen existente
            products.setImagen(p.getImagen());
        } else { // Si se selecciona un nuevo archivo de imagen, actualizamos la imagen
            // Eliminar la imagen anterior si no es la imagen por defecto
            if (!p.getImagen().equals("default.jpg")) {
                uploadFileService.deleteImage(p.getImagen());
            }
            String nombreImagen = uploadFileService.saveImage(file);
            products.setImagen(nombreImagen);
        }
        // Mantenemos el usuario del producto
        //products.setUsuario(p.getUsuario());
        // Validar y establecer la categoría y el stock del producto
        Category category = categoryService.get(idCategoria).orElse(null);
        if (category == null) {
            LOGGER.error("Categoría no encontrada para id: {}", idCategoria);
            return "redirect:/errorPage"; // Manejar el caso donde no se encuentra la categoría
        }
        products.setCategory(category);

        // Actualizamos el producto
        productService.update(products);

        return "redirect:/productos";
    }

    @GetMapping("/delete/{idProducto}")
    public String delete(@PathVariable Long idProducto) {
        Products p = productService.get(idProducto).orElseThrow(() -> new RuntimeException("Producto no encontrado"));

        // Eliminar la imagen solo si no es la imagen por defecto
        if (p.getImagen() != null && !p.getImagen().equals("default.jpg")) {
            uploadFileService.deleteImage(p.getImagen());
        }

        productService.delete(idProducto);
        return "redirect:/productos";
    }
}
