package com.example.elevanaapi.controllers;


import com.example.elevanaapi.models.User;
import com.example.elevanaapi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

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

            if ((!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
                    || (!firstName.matches("^[A-Za-z]+( [A-Za-z]+)*$") || !lastName.matches("^[A-Za-z]+( [A-Za-z]+)*$"))
                    || (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")))
                return ResponseEntity.status(406).build();

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

            if ((!email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))
                    || (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,}$")))
                return ResponseEntity.status(406).build();

            String token = this.userService.login(email, password);

            if(token==null){
                return ResponseEntity.status(404).build();
            }
            return ResponseEntity.status(200).body(token);

        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }
}
