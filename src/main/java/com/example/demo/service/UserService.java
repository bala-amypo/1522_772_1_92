package com.example.demo.service;

import com.example.demo.entity.UserEntity;
import java.util.List;

public interface UserService {

    UserEntity save(UserEntity user);

    List<UserEntity> getAll();

    UserEntity getById(Long id);
}
