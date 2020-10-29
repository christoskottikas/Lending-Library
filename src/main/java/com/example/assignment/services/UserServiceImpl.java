package com.example.assignment.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.assignment.model.User;
import com.example.assignment.repositories.UserRepository;

@Service
@Transactional
public class UserServiceImpl implements UserInterface {

	@Autowired
	UserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public List<User> findAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public List<User> findAllGuests() {
		return userRepository.findGuests();
	}

	@Override
	public List<User> findAllEmployees() {
		return userRepository.findEmployees();
	}

	@Override
	public User findByUsername(String username) {
		return userRepository.findByUsername(username);
	}

	@Override
	public boolean checkLogin(String username, String password) {
		User user = userRepository.findByUsername(username);
		List<User> allUsers = userRepository.findAll();
		for (User u : allUsers) {
			if (user != null && username.equalsIgnoreCase(u.getUsername())
					&& passwordEncoder.matches(password, user.getPassword())) {
				return true;
			}
		}
		return false;
	}
}
