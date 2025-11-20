package com.example.elevanaapi.configurations;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvLoader {
    public static void load() {
        Dotenv dotenv = Dotenv.configure().load();
        System.setProperty("DataBase_URI", dotenv.get("DataBase_URI"));
        System.setProperty("DataBase_Name", dotenv.get("DataBase_Name"));
    }
}
