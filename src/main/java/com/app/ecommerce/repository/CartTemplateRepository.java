package com.app.ecommerce.repository;

import com.app.ecommerce.model.CartItemModel;
import com.mongodb.client.result.UpdateResult;
import org.springframework.data.mongodb.core.query.Update;

public interface CartTemplateRepository {

    UpdateResult addProduct(CartItemModel product, String email);
    UpdateResult deleteProduct(CartItemModel product, String email);
    UpdateResult increaseProductQuantity(CartItemModel product, String email);
    UpdateResult decreaseProductQuantity(CartItemModel product, String email);

}
