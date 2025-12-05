package com.example.elevanaapi.controllers;


import com.example.elevanaapi.dto.Response;
import com.example.elevanaapi.dto.ReviewRequest;
import com.example.elevanaapi.models.Review;
import com.example.elevanaapi.services.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @PostMapping
    public ResponseEntity<?> saveReview(@RequestBody ReviewRequest review,
                                        @RequestHeader(value = "Authorization", required = false) String authHeader){
        try{
            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                return ResponseEntity.status(406).body("Missing or invalid Authorization header");
            }
            String token = authHeader.substring(7);
            Response<Review> res = reviewService.save(token,review);
            if(res.getStatus()==200){
                return ResponseEntity.ok(res);
            }
            return ResponseEntity.status(res.getStatus()).body(res.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(406).body("Missing or invalid Authorization header");
        }
    }
    @GetMapping("/{productId}")
    public ResponseEntity<?> getReview(@PathVariable("productId") String productId){
        try{
            return ResponseEntity.ok().body(reviewService.getReviews(productId));
        }catch (Exception e){
            return ResponseEntity.status(406).body("Missing or invalid Authorization header");
        }
    }
}
