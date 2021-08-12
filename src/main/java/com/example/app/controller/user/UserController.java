package com.example.app.controller.user;

import com.example.app.dto.UserDTO;
import com.example.app.service.UserService;
import com.example.app.util.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.xml.bind.JAXBException;
import java.util.List;


@RestController
@RequestMapping("/api/v1")
public class UserController {
    UserService userService;
    ResponseBean responseBean;

    @Autowired
    public UserController(UserService userService, ResponseBean responseBean) {
        this.userService = userService;
        this.responseBean = responseBean;
    }

    @GetMapping("/user")
    public ResponseBean getUser() {
        List<UserDTO> userList = userService.getUsers();
        responseBean.setMessage("User fetched successfully");
        responseBean.setResult(userList);
        return  responseBean;
    }

    @PostMapping("/user")
    public ResponseBean createUser(@RequestBody UserDTO user) throws JAXBException {
        UserDTO userDTO =  userService.save(user);
        responseBean.setMessage("User created successfully");
        responseBean.setResult(userDTO);
        return  responseBean;
    }

}
