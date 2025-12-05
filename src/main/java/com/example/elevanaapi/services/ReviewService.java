package com.example.elevanaapi.services;


import com.example.elevanaapi.configurations.JWT;
import com.example.elevanaapi.dto.Response;
import com.example.elevanaapi.dto.ReviewRequest;
import com.example.elevanaapi.models.Product;
import com.example.elevanaapi.models.Review;
import com.example.elevanaapi.models.User;
import com.example.elevanaapi.repositories.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private UserService userService;

    @Autowired
    private JWT jwt;


    public Response<Review> save(String token, ReviewRequest reviewRequest) {
        Response<Review> response = new Response<>();
        String userId = jwt.extractUserId(token);
        User user = userService.getUserById(userId);
        if(user==null){
            response.setStatus(404);
            response.setMessage("User not found");
            return response;
        }
        String userName = user.getFirstName() + " "+ user.getLastName();
        List<Review> reviews = reviewRepository.findAllByProductIdAndUserName(reviewRequest.getProductId(),userName);
        if(!reviews.isEmpty()){
            response.setStatus(404);
            response.setMessage("You already reviewed!");
            return response;
        }
        Review review = new Review();
        review.setComment(reviewRequest.getComment());
        review.setProductId(reviewRequest.getProductId());
        review.setUserName(userName);
        review.setRating(reviewRequest.getRating());
        Product product = recalculateRating(reviewRequest.getProductId(), reviews,reviewRequest.getRating());
        productService.updateProduct(product);
        response.setStatus(200);
        response.setMessage("Review saved!");
        response.setBody(reviewRepository.save(review));
        return response;
    }

    public Product recalculateRating(String productId,List<Review> reviews,double rating){
        Product product = productService.getProductById(productId);
        double newrating = 0;
        for(Review review : reviews){
            newrating += review.getRating();
        }
        newrating += rating;
        newrating /= (reviews.size()+1);
        product.setRating(newrating);
        product.setReviews(product.getReviews() + 1);
        return product;
    }


    public List<Review> getReviews(String productId){
        return reviewRepository.findAllByProductId(productId);
    }
}
