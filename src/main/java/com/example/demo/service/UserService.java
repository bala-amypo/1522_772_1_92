package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import java.util.List;

public interface UserService {

    // Get all users
    List<UserEntity> getAllUsers();

    // Get user by email
    UserEntity getUserByEmail(String email);

    // Create new user
    UserEntity createUser(UserEntity user);
}
