package com.fotograbados.springv1.web.controller;


import com.fotograbados.springv1.domain.service.IUsuarioService;
import com.fotograbados.springv1.domain.service.UploadFileService;
import com.fotograbados.springv1.domain.service.gproducto.ICategoryService;
import com.fotograbados.springv1.domain.service.gproducto.IProductService;
import com.fotograbados.springv1.domain.service.gproducto.IStockService;
import com.fotograbados.springv1.persistence.entities.Users;
import com.fotograbados.springv1.persistence.entities.inventario.Category;
import com.fotograbados.springv1.persistence.entities.inventario.Products;
import com.fotograbados.springv1.persistence.entities.inventario.StockMatPri;
import com.fotograbados.springv1.web.server.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.slf4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
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
    private IStockService stockService;
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
        List<StockMatPri> stocks = stockService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("stocks", stocks);
        model.addAttribute("categories", categories);

        return "productos/create";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Products products,
                       @RequestParam("img") MultipartFile file,
                       @RequestParam("category.idCategoria") Long idCategoria,
                       @RequestParam("stock.idStockMat") Long idStockMat,
                       HttpSession session) throws IOException {
        LOGGER.info("Este es el objeto Producto {}", products);
        
//        Object idUsuarioObj = session.getAttribute("idUsuario");
//        if (idUsuarioObj == null) {
//            LOGGER.error("El atributo 'idUsuario' en la sesión es null.");
//            // Maneja el caso en que el ID del usuario no esté presente en la sesión
//            return "redirect:/errorPage"; // O cualquier página de error apropiada
//        }
//
//        Long idUsuario = Long.parseLong(idUsuarioObj.toString());
//        Users u = usuarioService.findById(idUsuario).orElse(null);
        
        //if (u == null) {
         //   LOGGER.error("Usuario no encontrado para id: {}", idUsuario);
            // Maneja el caso en que el usuario no se encuentra en la base de datos
          //  return "redirect:/errorPage"; // O cualquier página de error apropiada
        //}
    
//        products.setUsuario(u);

        // Guardar la imagen y obtener el nombre
        if (products.getIdProducto() == null) {
            String nombreImagen = uploadFileService.saveImage(file);
            products.setImagen(nombreImagen);
        }

        // Validar y establecer la categoría y el stock del producto
        Category category = categoryService.get(idCategoria).orElse(null);
        StockMatPri stock = stockService.get(idStockMat).orElse(null);

        if (category == null || stock == null) {
            LOGGER.error("Categoría o Stock no encontrados.");
            return "redirect:/errorPage"; // Maneja el error si la categoría o el stock no están disponibles
        }

        products.setCategory(category);
        products.setStock(stock);

        // Guardar el producto
        productService.save(products);
        return "redirect:/productos";
    }

    @GetMapping("/edit/{idProducto}")
    public String edit(@PathVariable Long idProducto, Model model) {
        Products products = new Products();
        Optional<Products> optionalProducts = productService.get(idProducto);
        products = optionalProducts.get();

        LOGGER.info("Producto buscado: {}", products);
        model.addAttribute("products", products);

        List<StockMatPri> stocks = stockService.findAll();
        List<Category> categories = categoryService.findAll();
        model.addAttribute("stocks", stocks);
        model.addAttribute("category", categories);
        return "productos/edit";
    }
    @PostMapping("/update")
    public String update(@ModelAttribute("producto") Products products, @RequestParam("img") MultipartFile file) throws IOException {
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

        // Actualizamos el producto
        productService.update(products);

        return "redirect:/productos";
    }


    @GetMapping("/delete/{idProducto}")
    public String delete(@PathVariable Long idProducto) {

        Products p = new Products();
        p = productService.get(idProducto).get();

        // eliminar cuando no sea la imagen por defecto
        if (!p.getImagen().equals("default.jpg")) {
           uploadFileService.deleteImage(p.getImagen());
        }

        productService.delete(idProducto);
        return "redirect:/productos";
    }
                        //STOCK
    @GetMapping("/showStock")
    public String showStock(Model model){
        model.addAttribute("stock", stockService.findAll());
        return "";
    }

    @GetMapping("/createStock")
    public String createStock() {
        return "";
    }
    @PostMapping("/saveStock")
    public String saveStock(StockMatPri matPri, HttpSession session){
        LOGGER.info("Este es el objeto stock {}", matPri);

        Users u = usuarioService.findById(Long.parseLong(session.getAttribute("idUsuario").toString())).get();
        matPri.setUsuario(u);

        stockService.save(matPri);
        return "";
    }
    @GetMapping("/editStock/{idStockMat}")
    public String editStock(@PathVariable Long idStockMat, Model model) {
        StockMatPri stockMatPri = new StockMatPri();
        Optional<StockMatPri> optionalStockMatPri = stockService.get(idStockMat);
        stockMatPri = optionalStockMatPri.get();

        LOGGER.info("stock buscado: {}", stockMatPri);
        model.addAttribute("producto", stockMatPri);
        return "";
    }

    @PostMapping("/updateStock")
    public String updateStock(StockMatPri matPri) {
        StockMatPri sm = new StockMatPri();
        sm = stockService.get(matPri.getIdStockMat()).get();

        matPri.setUsuario(sm.getUsuario());
        stockService.update(matPri);
        return "redirect:/productos";
    }

    @GetMapping("/deleteStock/{idStockMat}")
    public String deleteStock(@PathVariable Long idStockMat) {

        StockMatPri sm = new StockMatPri();
        sm = stockService.get(idStockMat).get();

        stockService.delete(idStockMat);
        return "redirect:/productos";
    }


}
