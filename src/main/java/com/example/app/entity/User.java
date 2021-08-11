package com.example.app.entity;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class User {
	private Integer id;
	private String name;
	private Integer age;
	private String email;

	public User() {
		super();
	}
	
	public User(Integer id, String name, Integer age, String email) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public User(String name, Integer age, String email) {
		super();
		this.name = name;
		this.age = age;
		this.email = email;
	}
	
	public String getName() {
		return name;	
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
	public Integer getAge() {
		return age;
	}
	

	public void setAge(Integer age) {
		this.age = age;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", email=" + email + "]";
	}
	
}

