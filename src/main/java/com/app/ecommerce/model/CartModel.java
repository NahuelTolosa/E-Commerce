package com.app.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("carts")
public class CartModel {

    @Id
    private String id;
    private String email;
    private String date;
    private List<CartItemModel> products = new ArrayList<>();
    private String adress;
}
