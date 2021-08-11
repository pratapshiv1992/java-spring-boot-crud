package com.example.app.service;

import com.example.app.entity.User;
import com.example.app.util.XMLUtil;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import javax.xml.bind.JAXBException;

@Service
public class UserServiceImp implements UserService {
    public UserServiceImp() {
    }

    @Override
    public String getUsers() {
        return  "get user returned";
    }

    @Override
    public User save(User user) throws JAXBException {
        String xmlDoc = XMLUtil.objectToXMLConverter(user);
        user.setXml(xmlDoc);
        return user;
    }


}
