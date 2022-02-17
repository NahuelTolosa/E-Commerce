package com.app.ecommerce.repository.implementation;

import com.app.ecommerce.model.CartItemModel;
import com.app.ecommerce.repository.CartTemplateRepository;
import com.mongodb.client.result.UpdateResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;


@Repository
public class CartTemplateRepositoryImplementation implements CartTemplateRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public UpdateResult addProduct(CartItemModel product, String email) {

        return mongoTemplate.updateMulti(   new Query().addCriteria(Criteria.where("email").is(email)),
                                            new Update().push("products", product),
                                            "carts");

    }

    @Override
    public UpdateResult deleteProduct(CartItemModel product, String email) {

        return mongoTemplate.updateMulti(   new Query().addCriteria(Criteria.where("email").is(email)),
                                            new Update().pull("products", product),
                                            "carts");
    }

    @Override
    public UpdateResult increaseProductQuantity(CartItemModel product, String email) {
        return mongoTemplate.updateMulti(new Query().addCriteria(Criteria.where("email").is(email)).addCriteria(Criteria.where("products.name").is(product.getName())),
                new Update().inc("products.$.quantity",1),
                "carts");
    }

    @Override
    public UpdateResult decreaseProductQuantity(CartItemModel product, String email) {
        return mongoTemplate.updateMulti(new Query().addCriteria(Criteria.where("email").is(email)).addCriteria(Criteria.where("products.name").is(product.getName())),
                new Update().inc("products.$.quantity",-1),
                "carts");
    }
}
