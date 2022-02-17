package com.app.ecommerce.service;

import com.app.ecommerce.model.CartItemModel;
import com.app.ecommerce.model.CartModel;
import com.app.ecommerce.repository.CartRepository;
import com.app.ecommerce.repository.implementation.CartTemplateRepositoryImplementation;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartTemplateRepositoryImplementation cartTemplateRepository;

    public CartModel findByEmail(String email) {
        return this.cartRepository.findByEmail(email);
    }

    public String addProduct(CartItemModel product, String email) {

        UpdateResult updateResult =  this.cartTemplateRepository.addProduct(product,email);

        if (updateResult.getMatchedCount() == 1)
            return "Producto agregado correctamente.";

        return "Error. El Producto no pudo ser agregado.";

    }

    public String deleteProduct(CartItemModel product, String email) {

        UpdateResult updateResult =  this.cartTemplateRepository.deleteProduct(product,email);

        if (updateResult.getMatchedCount() == 1)
            return "Producto eliminado correctamente.";

        return "Error. El Producto no pudo ser eliminado.";

    }

    public String increaseProductQuantity(CartItemModel product, String email) {

        UpdateResult updateResult = this.cartTemplateRepository.increaseProductQuantity(product,email);

        if (updateResult.getMatchedCount() == 1)
            return "+1";

        return "Error";
    }

    public String decreaseProductQuantity(CartItemModel product, String email) {

        UpdateResult updateResult = this.cartTemplateRepository.decreaseProductQuantity(product,email);

        if (updateResult.getMatchedCount() == 1)
            return "-1";

        return "Error.";
    }

}
