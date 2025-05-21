package com.jspm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jspm.model.User;
import com.jspm.repository.UserRepository;
import com.jspm.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

	@Override
    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }
	@Override
	 public boolean authenticate(String username, String password) {
	        User user = userRepository.findByUsername(username);
	        return user != null && user.getPassword().equals(password);
	    }

}
