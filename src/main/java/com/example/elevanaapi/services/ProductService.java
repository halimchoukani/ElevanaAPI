package com.example.elevanaapi.services;

import com.example.elevanaapi.dto.ProductRequestDto;
import com.example.elevanaapi.models.Product;
import com.example.elevanaapi.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CloudinaryService cloudinaryService;

    @Autowired
    private CategoryService categoryService;

    public Product addProduct(ProductRequestDto productRequestDto) {
        Product product = new Product();
        product.setName(productRequestDto.getName());
        product.setDescription(productRequestDto.getDescription());
        if(categoryService.findCategoryById(productRequestDto.getCategory())==null){
            assert productRequestDto.getCategoryName() != null;
            categoryService.createCategory(productRequestDto.getCategoryName(),productRequestDto.getCategoryImage(),1);
        }
        product.setCategory(productRequestDto.getCategory());
        product.setOriginalPrice(productRequestDto.getOriginalPrice());
        product.setPrice(productRequestDto.getPrice());
        product.setStock(productRequestDto.getStock());
        product.setFeatures(productRequestDto.getFeatures());
        String imageUrl = cloudinaryService.uploadFile(productRequestDto.getImage(), "elevana/products");
        if(imageUrl == null){
            return null;
        }
        product.setImage(imageUrl);
        product.setSlug(productRequestDto.getName().replaceAll(" ", "-").trim().toLowerCase());
        List<String> imagesUrl = new ArrayList<>();
        imagesUrl.add(imageUrl);
        for (MultipartFile image : productRequestDto.getImages()) {
            imagesUrl.add(cloudinaryService.uploadFile(image, "elevana/products"));
        }
        product.setImages(imagesUrl);
        return productRepository.save(product);
    }


    public List<Product> allProducts(){
        return productRepository.findAll();
    }

    public Product getProduct(String slug) {
        return productRepository.getProductBySlug(slug);
    }
}
