package com.app.ecommerce.repository.interfaces;

import com.app.ecommerce.model.CartModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface CartRepository extends MongoRepository<CartModel, Serializable> {

    CartModel findByEmail(String email);
    void deleteByEmail(String email);
}
