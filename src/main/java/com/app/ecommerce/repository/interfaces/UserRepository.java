package com.app.ecommerce.repository.interfaces;

import com.app.ecommerce.model.UserModel;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<UserModel,String> {
    UserModel findByEmail(String email);
}
