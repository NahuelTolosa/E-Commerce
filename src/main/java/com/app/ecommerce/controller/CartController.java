package com.app.ecommerce.controller;

import com.app.ecommerce.model.CartItemModel;
import com.app.ecommerce.model.CartModel;
import com.app.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    @GetMapping("/get")
    public CartModel getCart(@RequestParam String email) {
        return this.cartService.findByEmail(email);
    }

    @PutMapping("/add")
    public String addProduct(@RequestBody CartItemModel product, @RequestParam String email) {
        return this.cartService.addProduct(product, email);
    }

    @DeleteMapping("/delete")
    public String deleteProduct(@RequestBody CartItemModel product, @RequestParam String email){
        return this.cartService.deleteProduct(product,email);
    }

    @PutMapping("/increase")
    public String increaseProductQuantity(@RequestBody CartItemModel product, @RequestParam String email) {
        return this.cartService.increaseProductQuantity(product,email);
    }

    @PutMapping("/decrease")
    public String decreaseProductQuantity(@RequestBody CartItemModel product, @RequestParam String email) {
        return this.cartService.decreaseProductQuantity(product,email);
    }

}
