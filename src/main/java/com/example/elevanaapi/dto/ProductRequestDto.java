package com.example.elevanaapi.dto;

import com.mongodb.lang.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequestDto {
    private String name;
    private String description;
    private String category;
    private MultipartFile image;
    private List<MultipartFile> images;
    private double price;
    private double originalPrice;
    private int stock;
    private List<String>features;
    @Nullable
    private MultipartFile categoryImage;
    @Nullable
    private String categoryName;
}
