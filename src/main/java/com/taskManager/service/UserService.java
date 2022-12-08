package com.taskManager.service;

import com.taskManager.model.entity.User;


public interface UserService {
    User findByEmail(String email);
    boolean saveUser(User user);
    User findByUsername(String username);
    User getAuthUser();
    boolean checkAvailabilityEmail(String email);
}
