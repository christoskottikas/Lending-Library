package com.example.assignment.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.assignment.model.User;
import com.example.assignment.services.UserInterface;

@RestController
public class UserController {

	@Autowired
	UserInterface userInterface;

	@ResponseBody
	@GetMapping("/getAllUsers")
	public List<User> getAllUsers() {
		return userInterface.findAllUsers();
	}

	@ResponseBody
	@GetMapping("/getAllGuests")
	public List<User> getAllGuests() {
		return userInterface.findAllGuests();
	}

	@ResponseBody
	@GetMapping("/getAllEmployees")
	public List<User> getAllEmployees() {
		return userInterface.findAllEmployees();
	}

	@ResponseBody
	@GetMapping("/login/{username}/{password}")
	public String login(@PathVariable("username") String username, @PathVariable("password") String password) {
		User user = userInterface.findByUsername(username);
		if (user != null) {
			if (user.getRoleID().getRoleId() == 1 && userInterface.checkLogin(username, password)) {
				return "Welcome Guest";
			} else if (user.getRoleID().getRoleId() == 2 && userInterface.checkLogin(username, password)) {
				return "Welcome Employee";
			}
		}
		return "Wrong credentials";
	}
}
