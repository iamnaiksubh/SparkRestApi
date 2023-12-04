package com.dnb.sparkdemo.services;

import java.util.Collection;
import java.util.List;

import com.dnb.sparkdemo.dto.User;

public interface UserService {
	public void addUser(User user);
	public Collection<User> getUsers();
	public User getUser(Integer userId);
	public Boolean updateUser(User user);
	public Boolean deleteUser(Integer userId);
}
