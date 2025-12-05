package com.example.elevanaapi.services;


import com.example.elevanaapi.configurations.JWT;
import com.example.elevanaapi.models.CartItem;
import com.example.elevanaapi.models.Order;
import com.example.elevanaapi.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private JWT jwt;


    public double getTotalAmount(List<CartItem> cartItems) {
        double totalAmount = 0;
        for (CartItem cartItem : cartItems) {
            totalAmount += cartItem.getProduct().getPrice() * cartItem.getQuantity();
        }
        return totalAmount;
    }

    public Order save(List<CartItem> cartItems,String shippingAddress,String token) {
        Order order = new Order();
        order.setCartItems(cartItems);
        order.setTotalAmount(getTotalAmount(cartItems));
        order.setShippingAddress(shippingAddress);
        String userid = jwt.extractUserId(token);
        order.setUserId(userid);
        return orderRepository.save(order);
    }
}
