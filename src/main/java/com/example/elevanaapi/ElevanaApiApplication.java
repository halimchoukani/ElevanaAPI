package com.example.elevanaapi;

import com.example.elevanaapi.configurations.EnvLoader;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ElevanaApiApplication {

    public static void main(String[] args) {
        EnvLoader.load();
        SpringApplication.run(ElevanaApiApplication.class, args);
    }

}
