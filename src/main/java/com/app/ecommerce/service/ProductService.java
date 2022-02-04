package com.app.ecommerce.service;

import com.app.ecommerce.model.ProductModel;
import com.app.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;

    public List<ProductModel> findAll() {
        return this.productRepository.findAll();
    }

    public ProductModel findByName(String name) {
        return this.productRepository.findByName(name);
    }

    public List<ProductModel> findByCategory(String category) {
        return this.productRepository.findByCategory(category);
    }



}
