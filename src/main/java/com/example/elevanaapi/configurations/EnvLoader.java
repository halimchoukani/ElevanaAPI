package com.example.elevanaapi.configurations;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    public static void load() {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("DataBase_URI", dotenv.get("DataBase_URI"));
        System.setProperty("DataBase_Name", dotenv.get("DataBase_Name"));
        System.setProperty("CLOUDINARY_NAME", dotenv.get("CLOUDINARY_NAME"));
        System.setProperty("CLOUDINARY_API_KEY", dotenv.get("CLOUDINARY_API_KEY"));
        System.setProperty("CLOUDINARY_API_SECRET", dotenv.get("CLOUDINARY_API_SECRET"));
    }
}
