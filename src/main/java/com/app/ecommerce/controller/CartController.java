package com.app.ecommerce.controller;

import com.app.ecommerce.model.CartItemModel;
import com.app.ecommerce.model.CartModel;
import com.app.ecommerce.model.OrderModel;
import com.app.ecommerce.service.CartService;
import com.app.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;
    private final OrderService orderService;

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
    public Boolean increaseProductQuantity(@RequestBody CartItemModel product, @RequestParam String email) {
        return this.cartService.increaseProductQuantity(product,email);
    }

    @PutMapping("/decrease")
    public Boolean decreaseProductQuantity(@RequestBody CartItemModel product, @RequestParam String email) {
        return this.cartService.decreaseProductQuantity(product,email);
    }

    @DeleteMapping
    public Boolean deleteCart(@RequestParam String email) {
        return this.cartService.deleteByEmail(email);
    }

    @GetMapping("/buy")
    public OrderModel buyCart(@RequestParam String email, @RequestParam String adress) {

        return this.orderService.createOrder(email,adress);

    }
}
