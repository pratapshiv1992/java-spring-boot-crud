package com.example.app.service;

import com.example.app.dto.UserDTO;
import com.example.app.entity.UserEntity;
import com.example.app.repository.UserRepository;
import com.example.app.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;

@Service
public class UserServiceImp implements UserService {
    UserRepository userRepo;

    @Autowired
    public UserServiceImp(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public String getUsers() {
        return "get user returned";
    }

    @Override
    public UserDTO save(UserDTO userDto) throws JAXBException {
        String xmlDoc = XMLUtil.objectToXMLConverter(userDto);
        UserEntity userEntity = new UserEntity();
        userEntity.setXml(xmlDoc);
        UserEntity result = userRepo.save(userEntity);
        userDto.setXml(result.getXml());
        userDto.setId(result.getId());
        return userDto;
    }

}
