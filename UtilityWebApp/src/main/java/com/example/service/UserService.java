package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.entities.User;
import com.example.repositories.UserRepository;

@Service
public class UserService {
		@Autowired
		UserRepository repository;
		@Autowired
		BCryptPasswordEncoder bCryptPasswordEncoder;
		
		public void save(User user) throws Exception {
			
			if(checkIsUserAlreadyExist(user.getUsername())) {
				throw new Exception("User already exists for this email");
			}
			
	        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
	        user.setEnabled(true); // Enable user by default
	        user.setRole("ROLE_USER");
	        repository.save(user);
	    }
		
		public boolean checkIsUserAlreadyExist(String name) {
			return repository.findByUsername(name) != null ? true : false;
		}
}
