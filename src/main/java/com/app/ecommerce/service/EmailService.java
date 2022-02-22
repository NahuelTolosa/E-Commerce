package com.app.ecommerce.service;

import com.app.ecommerce.model.OrderItemModel;
import com.app.ecommerce.model.OrderModel;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendOrderEmail(OrderModel order) {
        //Creo el template del mail
        var message = new SimpleMailMessage();

        //Creo el contenido del mail
        message.setTo(order.getEmail());
        message.setSubject("Confirmación de compra");
        message.setText("Gracias por su compra!\n\n\nDetalle:\n\n"+ printProducts(order));

        //Envio el mail
        this.javaMailSender.send(message);
    }

    private String printProducts(OrderModel order) {

        StringBuilder sb = new StringBuilder();
        sb.append("Producto\t\tCantidad\t\tPrecio\n\n");

        for (OrderItemModel product : order.getProducts()) {
            sb.append(product.getName()+"\t\t\t"+product.getQuantity()+"\t\t\t"+product.getPrice()+"\n");
        }

        sb.append("\n\n\nTotal: "+order.getTotal());

        return sb.toString();
    }
}
