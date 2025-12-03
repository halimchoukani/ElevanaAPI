package com.example.elevanaapi.services;

import com.example.elevanaapi.configurations.JWT;
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

    public Map<Integer, Object> addToCart(String productId, String token,int quantity) {
        Optional<User> userResponce = userRepository.findById(jwt.extractUserId(token));
        Map<Integer,Object> map = new HashMap<>();

        User user = null;
        if(userResponce.isPresent()) {
            user = userResponce.get();
        }
        if(user==null) {
            map.put(404,"User not found");
            return map;
        }
        Product product = productService.getProduct(productId);

        if(product == null) {
            map.put(404, "Product not found");
            return map;
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        List<CartItem> cartItems = user.getCart();
        cartItems.add(cartItem);
        user.setCart(cartItems);
        userRepository.save(user);
        map.put(200,"Product successfully added to cart");
        return map;
    }
    public Map<Integer, Object> removeCart(String productId, String token,int quantity) {
        Optional<User> userResponce = userRepository.findById(jwt.extractUserId(token));
        Map<Integer,Object> map = new HashMap<>();

        User user = null;
        if(userResponce.isPresent()) {
            user = userResponce.get();
        }
        if(user==null) {
            map.put(404,"User not found");
            return map;
        }
        Product product = productService.getProduct(productId);

        if(product == null) {
            map.put(404, "Product not found");
            return map;
        }
        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setQuantity(quantity);
        List<CartItem> cartItems = user.getCart();
        cartItems.remove(cartItem);
        user.setCart(cartItems);
        userRepository.save(user);
        map.put(200,"Product successfully removed from cart");
        return map;
    }
}
