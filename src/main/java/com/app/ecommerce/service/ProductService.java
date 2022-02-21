package com.app.ecommerce.service;

import com.app.ecommerce.model.ProductModel;
import com.app.ecommerce.repository.implementation.RedisRepositoryImplementation;
import com.app.ecommerce.repository.interfaces.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final RedisRepositoryImplementation<ProductModel> cache;

    public List<ProductModel> findAll() {
        return this.productRepository.findAll();
    }

    public ProductModel findById(String id) {
        //Busco en caché
        ProductModel dataFromCache =  this.cache.get(id, ProductModel.class);

        //Si lo encuentra, lo retorna
        if(!Objects.isNull(dataFromCache)) return dataFromCache;

        //Busco en DB
        ProductModel dataFromDB =this.productRepository.findById(id).get();

        //Lo almaceno en caché y lo retorno
        return this.cache.save(dataFromDB.getId(),dataFromDB);
    }

    public ProductModel findByName(String name) {
        return this.productRepository.findByName(name);
    }

    public List<ProductModel> findByCategory(String category) {
        return this.productRepository.findByCategory(category);
    }

    public ProductModel save(ProductModel product) {
        //Guardo en DB
        ProductModel data = this.productRepository.save(product);

        //Guardo en caché y retorno
        return this.cache.save(data.getId(),data);
    }

    public void deleteById(String id) {
        this.productRepository.deleteById(id);
    }
}
