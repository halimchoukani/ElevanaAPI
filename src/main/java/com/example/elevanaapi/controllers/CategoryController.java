package com.example.elevanaapi.controllers;

import com.example.elevanaapi.models.Category;
import com.example.elevanaapi.services.CategoryService;
import com.example.elevanaapi.services.CloudinaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<?> getCategories() {
        try {
            List<Category> categoryList = categoryService.getCategories();
            return ResponseEntity.ok().body(categoryList);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCategory(@PathVariable String id) {
        try{
            Category category = categoryService.findCategoryById(id);
            if(category == null){
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok().body(category);
        }catch (Exception e){
            return ResponseEntity.badRequest().build();
        }
    }
}
