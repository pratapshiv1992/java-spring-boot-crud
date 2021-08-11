package com.example.app.entity;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Integer id;
	private String name;
	private Integer age;
	private String email;
	private String xml;
}

