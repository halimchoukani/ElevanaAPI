package com.example.elevanaapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.minidev.json.annotate.JsonIgnore;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Data
public class User {
    @Id
    private String id;

    private String email;
    @JsonIgnore
    private String password;
    private String firstName;
    private String lastName;
    private String adresse;
    private String phone;
    private List<CartItem> cart;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


}
