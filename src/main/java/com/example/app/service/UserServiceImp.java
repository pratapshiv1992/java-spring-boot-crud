package com.example.app.service;

import com.example.app.entity.User;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    public UserServiceImp() {
    }

    @Override
    public void save(User user) {

    }

    @Override
    public String getUsers() {
        return  "get user returned";
    }
}
