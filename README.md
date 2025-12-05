# 🛍️ ElevanaAPI

ElevanaAPI is a robust and scalable RESTful API built with **Spring Boot** and **MongoDB** designed to power modern e-commerce platforms. It provides comprehensive features for user management, product cataloging, order processing, and reviews.

---

## 🚀 Tech Stack

*   **Java 21** ☕
*   **Spring Boot 3** 🍃
*   **MongoDB** 🍃
*   **Spring Security & JWT** 🔐
*   **Lombok** 🌶️
*   **Maven** 🛠️

---

## ✨ Features

*   **User Authentication**: Secure sign-up and login using JWT (JSON Web Tokens).
*   **Product Management**: Browse products, view details, and filter by category.
*   **Shopping Cart**: Add and remove items from a persistent user cart.
*   **Order System**: Place orders and track order history.
*   **Reviews**: Users can leave ratings and reviews for products.
*   **Categories**: Organized product categorization.

---

## 🛠️ Getting Started

### Prerequisites

*   **Java JDK 21** or higher
*   **Maven**
*   **MongoDB** (Local or Atlas)

### Installation

1.  **Clone the repository**
    ```bash
    git clone https://github.com/yourusername/ElevanaAPI.git
    cd ElevanaAPI
    ```

2.  **Configuration**
    Create a `.env` file or configure `application.properties` with your MongoDB URI and JWT secret.
    ```properties
    spring.data.mongodb.uri=mongodb://localhost:27017/elevana
    jwt.secret=your_super_secret_key
    ```

3.  **Build the project**
    ```bash
    mvn clean install
    ```

4.  **Run the application**
    ```bash
    mvn spring-boot:run
    ```

The API will be available at `http://localhost:8080`.

---

## 📚 API Documentation

### 👤 User Management

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `POST` | `/users/sign-up` | Register a new user account | ❌ |
| `POST` | `/users/login` | Authenticate user and get JWT token | ❌ |
| `PUT` | `/users/add-cart` | Add a product to the user's cart | ✅ |
| `DELETE` | `/users/remove-cart` | Remove a product from the user's cart | ✅ |

### 📦 Products

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `GET` | `/products` | Get all products (optional query `?category=name`) | ❌ |
| `GET` | `/products/{slug}` | Get detailed information of a specific product | ❌ |
| `POST` | `/products/add` | Add a new product to the catalog | ⚠️ (Admin) |

### 📂 Categories

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `GET` | `/categories` | Retrieve a list of all categories | ❌ |
| `GET` | `/categories/{id}` | Get details of a specific category | ❌ |

### 🛒 Orders

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `POST` | `/orders` | Place a new order with items in the cart | ✅ |
| `GET` | `/orders` | Retrieve the logged-in user's order history | ✅ |

### ⭐ Reviews

| Method | Endpoint | Description | Auth Required |
| :--- | :--- | :--- | :---: |
| `POST` | `/reviews` | Submit a review for a product | ✅ |
| `GET` | `/reviews/{productId}` | Get all reviews for a specific product | ❌ |

---

## 📂 Project Structure

```
com.example.elevanaapi
├── configurations  # Security & App Configs (JWT, CORS)
├── controllers     # REST Controllers (API Endpoints)
├── dto             # Data Transfer Objects
├── models          # MongoDB Documents (User, Product, Order, etc.)
├── repositories    # Spring Data MongoDB Repositories
└── services        # Business Logic Layer
```

---

## 🛡️ Security

This API uses **JWT (JSON Web Token)** for stateless authentication.
*   **Public Endpoints**: Login, Sign-up, Product browsing.
*   **Protected Endpoints**: Cart operations, Ordering, Reviewing.
*   **Header Format**: `Authorization: Bearer <your_token>`

---

Made with ❤️ by the Elevana Team.
