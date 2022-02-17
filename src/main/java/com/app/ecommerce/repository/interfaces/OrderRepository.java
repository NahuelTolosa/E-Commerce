package com.app.ecommerce.repository.interfaces;

import com.app.ecommerce.model.OrderModel;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface OrderRepository extends MongoRepository<OrderModel, Serializable> {
}
