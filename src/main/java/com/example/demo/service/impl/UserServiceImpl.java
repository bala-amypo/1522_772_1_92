package com.example.demo.service.impl;

import com.example.demo.entity.UserEntity;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepo;

    public UserServiceImpl(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserEntity save(UserEntity user) {
        return userRepo.save(user);
    }

    @Override
    public List<UserEntity> getAll() {
        return userRepo.findAll();
    }

    @Override
    public UserEntity getById(Long id) {
        return userRepo.findById(id).orElse(null);
    }
}
