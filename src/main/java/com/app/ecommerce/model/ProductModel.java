package com.app.ecommerce.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("products")
public class ProductModel {

    @Id
    private String id;
    private String name;
    private String description;
    private String category;
    private Double price;
}
