package com.elevysi.site.auth.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.elevysi.site.auth.dao.UserDAO;
import com.elevysi.site.auth.entity.User;


@Service
public class UserService extends AbstractService{
	
	private UserDAO userDAO;
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	public UserService(UserDAO userDAO, PasswordEncoder passwordEncoder){
		this.userDAO = userDAO;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User saveUser(User user){
		
		Date now = new Date();
		user.setActive(true);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode(user.getPassword()));
		user.setCreated(now);
		user.setModified(now);
		return userDAO.save(user);
	}
	
	public User loadUserByUsername(String username){
		return userDAO.loadByUsername(username);
	}

}
