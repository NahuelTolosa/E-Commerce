package com.app.ecommerce.service;

import com.app.ecommerce.model.CartItemModel;
import com.app.ecommerce.model.CartModel;
import com.app.ecommerce.model.OrderItemModel;
import com.app.ecommerce.model.OrderModel;
import com.app.ecommerce.repository.interfaces.CartRepository;
import com.app.ecommerce.repository.interfaces.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CartService cartService;
    private final EmailService emailService;

    public OrderModel createOrder(String email, String adress) {

        //Encuentro el carrito
        CartModel cart = this.cartService.findByEmail(email);

        //Si hay productos dentro creo la orden y la guardo
        if(cart.getProducts().size() > 0){
            OrderModel order = new OrderModel();

            order.setEmail(cart.getEmail());
            order.setDate(LocalDateTime.now());
            order.setAdress(adress);
            order.setProducts(this.cartToOrder(cart.getProducts()));

            //Borro el carrito
            this.cartService.deleteByEmail(email);

            //Envío el email
            this.emailService.sendOrderEmail(order);

            //Retorna la oden
            return this.orderRepository.insert(order);
        }

        return null;
    }

    private List<OrderItemModel> cartToOrder(List<CartItemModel> cart) {

        List<OrderItemModel> order = new ArrayList<>();

        for (CartItemModel cim : cart) {
            order.add(new OrderItemModel(cim.getName(),cim.getPrice(),cim.getQuantity()));
        }

        return order;

    }

}
