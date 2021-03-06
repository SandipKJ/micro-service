package com.course.microservices.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.course.microservices.model.User;

@Component
public class UserDao {

	private static List<User> users = new ArrayList<>();
	
	private static int usersCount = 3;
	
	static{
		users.add(new User(1,"Sandip",new Date()));
		users.add(new User(2,"Mandip",new Date()));
		users.add(new User(3,"Manisha",new Date()));
	}
	
	public List<User> findAll(){
		return users;
	}
	
	public User save(User user){
		if(user.getId() == null){
			user.setId(++usersCount);
		}
		users.add(user);
		return user;
	}
	
	public User findOne(int id){
		for(User user : users){
			if(user.getId() == id){
				return user;
			}
		}
		return null;
	}
	
	
	
}
