package com.example.elevanaapi.services;

import com.example.elevanaapi.configurations.JWT;
import com.example.elevanaapi.dto.Response;
import com.example.elevanaapi.models.CartItem;
import com.example.elevanaapi.models.Product;
import com.example.elevanaapi.models.User;
import com.example.elevanaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    private JWT jwt;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User getUserById(String id) {
        return userRepository.findById(id).orElse(null);
    }

    public String login(String email,String password) {
        try {
            Optional<Object> user = userRepository.findByEmail(email);
            if(user.isEmpty()) {
                return null;
            }
            User userObj = (User) user.get();
            boolean isValid = validatePassword(password, userObj.getPassword());
            if(!isValid) {
                return null;
            }
            return jwt.generateToken(userObj);
        }catch (Exception e){
            return null;
        }
    }
    public boolean validatePassword(String rawPassword, String encodedPassword) {
        return passwordEncoder.matches(rawPassword, encodedPassword);
    }
    public User signUp(String email, String password, String firstName, String lastName) {
        User user = new User();
        user.setEmail(email);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setPassword(password);
        user.setCreatedAt(LocalDateTime.now());
        if (userRepository.findByEmail((user.getEmail())).isPresent()) {
            return null;
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public Response<CartItem> addToCart(String productId, String token, int quantity) {
        Optional<User> userResponce = userRepository.findById(jwt.extractUserId(token));
        Response<CartItem> map = new Response<CartItem>();

        User user = null;
        if(userResponce.isPresent()) {
            user = userResponce.get();
        }
        if(user==null) {
            map.setStatus(404);
            map.setMessage("User not found");
            return map;
        }
        Product product = productService.getProductById(productId);
        if(product == null) {
            map.setStatus(404);
            map.setMessage("Product not found");
            return map;
        }
        if(product.getStock()<quantity) {
            map.setStatus(404);
            map.setMessage("Quantity is too high");
            return map;
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);

        List<CartItem> cartItems = user.getCart();
        cartItems.add(cartItem);
        user.setCart(cartItems);
        userRepository.save(user);
        map.setStatus(200);
        map.setMessage("Product added successfully");
        map.setBody(cartItem);
        return map;
    }
    public Response<CartItem> removeCart(String productId, String token,int quantity)     {
        Optional<User> userResponce = userRepository.findById(jwt.extractUserId(token));
        Response<CartItem> map = new Response<CartItem>();

        User user = null;
        if(userResponce.isPresent()) {
            user = userResponce.get();
        }
        if(user==null) {
            map.setStatus(404);
            map.setMessage("User not found");
            return map;
        }
        Product product = productService.getProductById(productId);
        if(product == null) {
            map.setStatus(404);
            map.setMessage("Product not found");
            return map;
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        List<CartItem> cartItems = user.getCart();
        cartItems.remove(cartItem);
        user.setCart(cartItems);
        userRepository.save(user);
        map.setStatus(200);
        map.setMessage("Product deleted from the cart successfully");
        map.setBody(cartItem);
        return map;
    }
}
