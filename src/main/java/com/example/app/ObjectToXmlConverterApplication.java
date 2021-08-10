package com.example.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import java.io.StringWriter;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("/api/v1")
public class ObjectToXmlConverterApplication {

	public static void main(String[] args) {
		SpringApplication.run(ObjectToXmlConverterApplication.class, args);
	}

	@PostMapping("/createUserXML")
	public ResponseObject createUserXML(@RequestBody User user) throws SQLException {
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(User.class);
			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			marshaller.marshal(user, sw);
			DbConnection.insertRecord("user", sw.toString());
			return new ResponseObject(200, sw.toString(), false);

		} catch (JAXBException e) {
			e.printStackTrace();
			return new ResponseObject(200, "", true);
		}
	}
	
	@GetMapping("/getUsers")
	public List<User> getUsers() throws SQLException {
		try {
			return DbConnection.getAllRecord();

		} catch (JAXBException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	@PutMapping("/updateUser/{id}")
	public String updateUser(@PathVariable Integer id, @RequestBody User user) throws JAXBException {
		Boolean isUserUpdate = DbConnection.updateUser(id, user);
		System.out.println(isUserUpdate);
		if(isUserUpdate) {
			return "{\"status\":200,\"message\":\"user updated successfully\"}";	
		} else {
			return "{\"status\": 404, \"message\": \"user updation failed\"}";
		}
	}

}
