package com.example.elevanaapi.services;

import com.example.elevanaapi.models.Category;
import com.example.elevanaapi.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    public List<Category> getCategories() {
        return categoryRepository.findAll();
    }
    public Category createCategory(String name, MultipartFile image) {
        Category category = new Category();
        category.setId(name.trim().toLowerCase().replace(" ","-"));
        category.setName(name);
        String imageUrl = cloudinaryService.uploadFile(image,"elevana/categories");
        if(imageUrl != null) {
            category.setImage(imageUrl);
            category.setProductCount(0);
            return categoryRepository.save(category);
        }
        return null;
    }


    public Category createCategory(String name, MultipartFile image, int productCount) {
        Category category = new Category();
        category.setId(name.trim().toLowerCase().replace(" ","-"));
        category.setName(name);
        String imageUrl = cloudinaryService.uploadFile(image,"elevana/categories");
        if(imageUrl != null) {
            category.setImage(imageUrl);
            category.setProductCount(productCount);
            return categoryRepository.save(category);
        }
        return null;
    }

    public Category findCategoryById(String id) {
        return categoryRepository.findById(id).orElse(null);
    }

    public Category updateProductCount(String id) {
        Category category = categoryRepository.findById(id).orElse(null);
        assert category != null;
        category.setProductCount(category.getProductCount() + 1);
        return categoryRepository.save(category);
    }
}
