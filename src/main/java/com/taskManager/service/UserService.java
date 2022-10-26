package com.taskManager.service;

import com.taskManager.model.entity.User;

import java.util.List;

public interface UserService {
    User findByEmail(String email);
    List<User> findAll();
    boolean saveUser(User user);
}
