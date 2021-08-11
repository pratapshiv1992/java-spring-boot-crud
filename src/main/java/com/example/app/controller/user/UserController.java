package com.example.app.controller.user;

import com.example.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;
import com.example.app.util.XMLUtil;
import com.example.app.entity.User;


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
    public String createUser(@RequestBody User user) throws JAXBException {
        String xmlDoc = XMLUtil.objectToXMLConverter(user);
        return xmlDoc;
    }

}