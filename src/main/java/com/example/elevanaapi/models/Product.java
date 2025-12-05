package com.example.elevanaapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    private String name;
    private String slug;
    private String description;
    private String category;
    private String image;
    private List<String> images;
    private double price;
    private double originalPrice;
    private double rating=0;
    private int stock=0;
    private int reviews=0;
    private List<String>features;
    private Date createdAt;
}
