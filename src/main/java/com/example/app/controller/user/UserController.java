package com.example.app.controller.user;

import com.example.app.dto.UserDTO;
import com.example.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getUser() {
        return userService.getUsers();
    }

    @PostMapping("/user")
    public UserDTO createUser(@RequestBody UserDTO user) throws JAXBException {
        return userService.save(user);
    }

}
