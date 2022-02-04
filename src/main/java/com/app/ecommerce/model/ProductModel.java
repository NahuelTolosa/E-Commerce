package com.app.ecommerce.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@Document("products")
public class ProductModel {

    @Id
    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
}
