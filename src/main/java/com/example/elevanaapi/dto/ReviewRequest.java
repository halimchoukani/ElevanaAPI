package com.example.elevanaapi.dto;

import lombok.Data;

@Data
public class ReviewRequest {
    private String productId;
    private String comment;
    private double rating;
}
