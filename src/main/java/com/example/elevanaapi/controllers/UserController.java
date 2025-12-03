package com.example.elevanaapi.controllers;


import com.example.elevanaapi.models.User;
import com.example.elevanaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("firstname") String firstName,
            @RequestParam("lastname") String lastName) {
        try {

            User user = this.userService.signUp(email, password, firstName, lastName);

            if(user==null){
                return ResponseEntity.status(406).build();
            }
            return ResponseEntity.status(200).build();

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestParam("email") String email,
            @RequestParam("password") String password
    ){
        try {

            String token = this.userService.login(email, password);

            if(token==null){
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.status(200).body(token);

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @PutMapping("/add-cart")
    public ResponseEntity<?> addToCart(@RequestBody String productId,@RequestBody int quantity,@RequestHeader String token){
        try{
            if(token==null){
                return ResponseEntity.status(406).build();
            }
            Map<Integer,Object> map = userService.addToCart(productId,token,quantity);
            return ResponseEntity.status(200).body(map);

        }catch (RuntimeException e){
            return ResponseEntity.status(400).build();
        }
    }


    @DeleteMapping("/remove-cart")
    public ResponseEntity<?> removeFromCart(@RequestBody String productId,@RequestBody int quantity,@RequestHeader String token){
        try{
            if(token==null){
                return ResponseEntity.status(406).build();
            }
            Map<Integer,Object> map = userService.removeCart(pproductId,token,quantity);
            return ResponseEntity.status(200).body(map);

        }catch (RuntimeException e){
            return ResponseEntity.status(400).build();
        }
    }
}
