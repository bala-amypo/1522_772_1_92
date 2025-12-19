package com.example.demo.controller;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserRepository repo;

    public UserController(UserRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    public UserEntity create(@Valid @RequestBody UserEntity user) {
        return repo.save(user);
    }

    @GetMapping
    public List<UserEntity> getAll() {
        return repo.findAll();
    }
}
