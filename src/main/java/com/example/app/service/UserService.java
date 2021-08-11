package com.example.app.service;
import com.example.app.entity.User;


public interface UserService {
    void save(User user);
    String getUsers();
}
