package com.example.elevanaapi.models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class CartItem {
    @DBRef
    private Product product;
    private int quantity;
}
