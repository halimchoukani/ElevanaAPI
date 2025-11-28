package com.example.elevanaapi.controllers;


import com.example.elevanaapi.dto.ProductRequestDto;
import com.example.elevanaapi.models.Product;
import com.example.elevanaapi.services.CloudinaryService;
import com.example.elevanaapi.services.ProductService;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
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

    @GetMapping("")
    public ResponseEntity<?> getProducts(@PathParam("category") String category) {
        try {
            if(category!=null){
                return getProductsByCategory(category);
            }
            List<Product> products = productService.allProducts();
            if(products != null){
                return ResponseEntity.ok(products.toArray(new Product[0]));
            }
            return ResponseEntity.status(406).build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(400).build();
        }
    }

    @GetMapping("/{slug}")
    public ResponseEntity<?> getProductById(@PathVariable String slug) {
        try{
            Product product = productService.getProduct(slug);
            if(product != null){
                return ResponseEntity.ok(Map.of("product", product));
            }
            return ResponseEntity.status(406).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(400).build();
        }
    }

    public ResponseEntity<?> getProductsByCategory(@PathParam("category") String category) {
        try{
            List<Product> productList = productService.getAllProductsByCategory(category);
            if(productList != null){
                return ResponseEntity.ok(productList.toArray(new Product[0]));
            }
            return ResponseEntity.status(406).build();
        }catch (RuntimeException e){
            return ResponseEntity.status(400).build();
        }
    }


}
