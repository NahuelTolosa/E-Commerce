package com.app.ecommerce.controller;

import com.app.ecommerce.model.ProductModel;
import com.app.ecommerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.*;

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


}
