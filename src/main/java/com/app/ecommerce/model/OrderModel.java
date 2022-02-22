package com.app.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@Document("orders")
public class OrderModel {

    @Id
    private String id;
    private String email;
    private LocalDateTime date;
    private Status status;
    private String adress;
    private List<OrderItemModel> products = new ArrayList<>();

    public OrderModel() {
        this.status = Status.GENERADA;
    }

    public OrderModel(String email, LocalDateTime date, String adress, List<OrderItemModel> products) {
        this.email = email;
        this.date = date;
        this.status = Status.GENERADA;
        this.adress = adress;
        this.products = products;
    }

    public Double getTotal() {
        Double total = 0.0;

        for (OrderItemModel product : this.getProducts()) {
            total += product.getTotal();
        }

        return total;
    }

    private enum Status {
        GENERADA,
        RECHAZADA,
        EN_PROCESO
    }
}
