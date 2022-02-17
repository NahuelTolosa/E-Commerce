package com.app.ecommerce.service;

import com.app.ecommerce.model.CartItemModel;
import com.app.ecommerce.model.CartModel;
import com.app.ecommerce.repository.interfaces.CartRepository;
import com.app.ecommerce.repository.implementation.CartTemplateRepositoryImplementation;
import com.mongodb.client.result.UpdateResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CartService {

    private final CartRepository cartRepository;
    private final CartTemplateRepositoryImplementation cartTemplateRepository;

    public CartModel findByEmail(String email) {
        return this.cartRepository.findByEmail(email);
    }

    public String addProduct(CartItemModel product, String email) {

        //Si no hay carrito creado, lo crea
        if(findByEmail(email) == null)
            this.cartRepository.insert(new CartModel(null,email, LocalDateTime.now(),null));

        //Lo busca y agrega el producto
        UpdateResult updateResult =  this.cartTemplateRepository.addProduct(product,email);

        //Si el producto es agregado retora un mensaje afirmativo
        if (updateResult.getMatchedCount() == 1)
            return "Producto agregado correctamente.";

        //Si el producto no es agregado retora un mensaje negativo
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

    public String deleteByEmail(String email) {
        this.cartRepository.deleteByEmail(email);
        return "Carrito eliminado";
    }

}
