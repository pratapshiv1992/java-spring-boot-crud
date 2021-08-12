package com.example.app.service;
import com.example.app.dto.UserDTO;

import javax.xml.bind.JAXBException;
import java.util.List;


public interface UserService {
    List <UserDTO> getUsers();
    UserDTO save(UserDTO user) throws JAXBException;
}
