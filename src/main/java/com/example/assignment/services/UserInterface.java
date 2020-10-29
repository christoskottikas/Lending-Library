package com.example.assignment.services;

import java.util.List;

import com.example.assignment.model.User;

public interface UserInterface {

	public List<User> findAllUsers();
	public List<User> findAllGuests();
	public List<User> findAllEmployees();
	public User findByUsername(String username);
	public boolean checkLogin(String username, String password);
}
