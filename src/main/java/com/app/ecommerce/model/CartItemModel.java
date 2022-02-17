package com.app.ecommerce.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
public class CartItemModel {

    private String name;
    private String description;
    private Double price;
    private Integer quantity;
}
