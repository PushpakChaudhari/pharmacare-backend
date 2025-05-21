package com.jspm.service;

import com.jspm.model.User;

public interface UserService {
    User findByUsername(String username);
    User save(User user);
	boolean existsByUsername(String username);
	public boolean authenticate(String username, String password);
//	 boolean authenticateByEmail(String email, String password);
//	  boolean existsByEmail(String email);
}
