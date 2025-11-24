package com.example.elevanaapi.configurations;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class CloudinaryConfiguration {


    @Bean
    public Cloudinary cloudinary() {
        return new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "dntenyj3n",
                "api_key", "331459876225144",
                "api_secret", "m8SgL_WrrhTh8e_QfP9qYun38A0"));
    }
}