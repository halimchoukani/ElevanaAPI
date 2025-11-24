package com.example.elevanaapi.controllers;


import com.example.elevanaapi.dto.ProductRequestDto;
import com.example.elevanaapi.models.Product;
import com.example.elevanaapi.services.CloudinaryService;
import com.example.elevanaapi.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping("/add")
    public ResponseEntity<?> add(ProductRequestDto productRequestDto) {
        try {
            Product product = productService.addProduct(productRequestDto);
            if(product != null){
                return ResponseEntity.ok(Map.of("product", product));
            }
            return ResponseEntity.status(406).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }


}
