package com.app.ecommerce.controller;

import com.app.ecommerce.handler.ErrorHandler;
import com.app.ecommerce.model.ProductModel;
import com.app.ecommerce.service.EmailService;
import com.app.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);
//    private final EmailService emailService;
    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductModel> findAll() {
//        emailService.sendTestEmail();
        return this.productService.findAll();
    }

    @GetMapping("/product")
    public ProductModel findByName(@RequestParam String name) {
        return this.productService.findByName(name);
    }

    @GetMapping
    public List<ProductModel> findByCategory(@RequestParam String category) {
        return this.productService.findByCategory(category);
    }

    @GetMapping("/product/{id}")
    public ProductModel findById(@PathVariable String id){
        try{
            return this.productService.findById(id);
        }catch (NoSuchElementException e){
            logger.error("Error en el metodo findById() - " + e.toString() + "\n");
        }
        return null;
    }


}
