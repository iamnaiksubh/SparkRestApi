package com.dnb.sparkdemo.services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

import com.dnb.sparkdemo.dto.User;

public class UserServiceImpl implements UserService{
	
	private HashMap<Integer, User> userMap;
	
	public UserServiceImpl() {
		userMap = new HashMap<>();
	}
	
	@Override
	public void addUser(User user) {
		userMap.put(user.getId(), user);
	}

	@Override
	public Collection<User> getUsers() {
		return userMap.values();
	}

	@Override
	public User getUser(Integer userId) {
		if (userMap.containsKey(userId)) {
			return userMap.get(userId);
		}
		
		return new User();
	}

}
