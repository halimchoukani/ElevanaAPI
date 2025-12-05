package com.example.elevanaapi.dto;

import com.example.elevanaapi.models.CartItem;
import lombok.Data;

import java.util.List;

@Data
public class OrderRequest {
    private List<CartItem> cartItems;
    private String shippingAddress;
}

