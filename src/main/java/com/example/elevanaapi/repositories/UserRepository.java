package com.example.elevanaapi.repositories;

import com.example.elevanaapi.models.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
    Optional<Object> findByEmail(String email);

    Optional<Object> findByPhone(String phone);
}
