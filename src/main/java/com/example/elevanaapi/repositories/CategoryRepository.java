package com.example.elevanaapi.repositories;

import com.example.elevanaapi.models.Category;
import com.example.elevanaapi.models.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CategoryRepository extends MongoRepository<Category, String> {
}
