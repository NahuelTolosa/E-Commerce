package com.app.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class OrderItemModel {

    private String  name;
    private Double  price;
    private Integer quantity;

    public Double getTotal() {
        return this.price * this.quantity;
    }
}
