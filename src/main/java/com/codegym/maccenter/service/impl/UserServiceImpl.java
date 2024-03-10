package com.codegym.maccenter.service.impl;

import com.codegym.maccenter.model.User;
import com.codegym.maccenter.repository.UserRepository;
import com.codegym.maccenter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;

	@Override
	public void updateUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void saveUser(User user) {
		userRepository.save(user);
	}

	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findUserByEmail(email);
	}

//	@Override
//	public boolean isEmailAlreadyInUse(String email) {
//		User existingUser = userRepository.findUserByEmail(email);
//		return existingUser != null;
//	}


	@Override
	public List<User> getAllUser() {
		return userRepository.findAll();
	}

}