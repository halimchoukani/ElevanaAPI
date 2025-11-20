package com.example.elevanaapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "categories")
@Data
public class Category {
    @Id
    private String id;
    private String name;
    private String image;
    private int productCount;
}
