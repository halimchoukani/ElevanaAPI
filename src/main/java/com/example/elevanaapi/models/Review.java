package com.example.elevanaapi.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "reviews")
@Data
public class Review{
    @Id
    private String id;
    private String userName;
    private String productId;
    private String comment;
    private Date date;
    private double rating;
}
