package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository userRepo;

    public UserController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @PostMapping
    public UserEntity saveUser(@RequestBody UserEntity user) {
        return userRepo.save(user);
    }

    @GetMapping
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    @GetMapping("/{id}")
    public UserEntity getUserById(@PathVariable Long id) {
        return userRepo.findById(id).orElse(null);
    }
}
