package com.school.lms.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.school.lms.entity.User;

@Service
public class UserService {
	private List<User> store = new ArrayList<>();

//	Default constructor
	public UserService() {
//		If we want multiple users access we can add more new user
		store.add(new User(1L, "Admin", "admin@gmail.com"));
	}

	public List<User> getUser() {
		return this.store;
	}
}
