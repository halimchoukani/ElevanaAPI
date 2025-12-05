package com.example.elevanaapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "orders")
@Data
public class Order {

    private enum Status {
        PROCESSING,
        DELIVERED,
    }

    @Id
    private String id;
    private String userId;
    private List<CartItem> cartItems =  new ArrayList<>();

    private double totalAmount;
    private String shippingAddress;
    private Date orderDate = new Date();
    private Status status =  Status.PROCESSING;

}
