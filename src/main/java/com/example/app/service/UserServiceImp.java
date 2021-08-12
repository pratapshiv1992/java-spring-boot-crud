package com.example.app.service;

import com.example.app.dto.UserDTO;
import com.example.app.entity.UserEntity;
import com.example.app.repository.UserRepository;
import com.example.app.util.XMLUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImp implements UserService {
    UserRepository userRepo;

    @Autowired
    public UserServiceImp(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public List<UserDTO> getUsers() {
        List<UserDTO> userDTOS = userRepo.findAll().stream().map(user -> {
            try {
                UserDTO userDTO = (UserDTO) XMLUtil.xmlToObjectConverter(user.getXml(), new UserDTO());
                userDTO.setId(user.getId());
                userDTO.setXml(user.getXml());
                return userDTO;
            } catch (JAXBException e) {
                e.printStackTrace();
            }
            return null;
        }).collect(Collectors.toList());
        return userDTOS;
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
