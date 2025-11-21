package com.example.elevanaapi.services;

import com.example.elevanaapi.configurations.JWT;
import com.example.elevanaapi.models.User;
import com.example.elevanaapi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

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
}
