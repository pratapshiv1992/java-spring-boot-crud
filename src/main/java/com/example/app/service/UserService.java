package com.example.app.service;
import com.example.app.entity.User;

import javax.xml.bind.JAXBException;


public interface UserService {
    String getUsers();
    User save(User user) throws JAXBException;
}
