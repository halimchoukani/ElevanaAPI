package com.example.elevanaapi.controllers;

import com.example.elevanaapi.dto.OrderRequest;
import com.example.elevanaapi.models.CartItem;
import com.example.elevanaapi.models.Order;
import com.example.elevanaapi.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;


    @PostMapping("")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequest request,
                                         @RequestHeader(value = "Authorization", required = false) String authHeader) {
        try{
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(406).body("Missing or invalid Authorization header");
            }

            // Extract the raw token
            String token = authHeader.substring(7);
            Order order = orderService.save(request.getCartItems(),request.getShippingAddress(),token);
            if(order!=null){
                return ResponseEntity.status(200).body(order);
            }
            return ResponseEntity.status(406).body(null);
        }catch(Exception e){
            return ResponseEntity.status(406).body(e.getMessage());
        }

    }
}
