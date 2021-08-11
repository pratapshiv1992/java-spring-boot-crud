package com.example.app.service;
import com.example.app.dto.UserDTO;

import javax.xml.bind.JAXBException;


public interface UserService {
    String getUsers();
    UserDTO save(UserDTO user) throws JAXBException;
}
