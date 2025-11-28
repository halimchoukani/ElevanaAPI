package com.example.elevanaapi.repositories;


import com.example.elevanaapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {
    Product getProductBySlug(String slug);

    @Query("{ 'category': { $regex: ?0, $options: 'i' } }")
    List<Product> getProductsByCategoryIgnoreCase(String category);
}
