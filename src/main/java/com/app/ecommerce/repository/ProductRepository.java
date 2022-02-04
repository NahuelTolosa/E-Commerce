package com.app.ecommerce.repository;

import com.app.ecommerce.model.ProductModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<ProductModel, Serializable> {
    public ProductModel findByName(String name);
    public List<ProductModel> findByCategory(String category);
}
