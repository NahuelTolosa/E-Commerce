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

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/all")
    public List<ProductModel> findAll() {
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

    @PostMapping("/product")
    public ProductModel findById(@RequestParam String id){
        return this.productService.findById(id);
    }

    @PutMapping("/save")
    public ProductModel save(@RequestBody ProductModel product) {
        return this.productService.save(product);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable String id) {
        this.productService.deleteById(id);
    }


}
