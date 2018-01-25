package com.elevysi.site.auth.test.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elevysi.site.auth.AuthBootApplication;
import com.elevysi.site.auth.config.AppConfig;
import com.elevysi.site.auth.dao.UserDAO;
import com.elevysi.site.auth.entity.User;
import com.elevysi.site.auth.service.UserService;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={AuthBootApplication.class, AppConfig.class})
public class UserServiceTest {
	
	@Autowired
	private PasswordEncoder passwordEncoder; 
	
	private UserDAO userDAO = mock(UserDAO.class);
	private UserService userService = new UserService(userDAO, passwordEncoder);
	private User user;
	
	@Before
	public void init(){
		this.user = new User();
		user.setFirst_name("John");
		user.setLast_name("Doe");
		user.setEmail("johnny@elevysi.com");
		user.setUsername("johnnie");
		user.setPassword("passHere");
	}
	
	@Test
	public void testSaveUser(){
		
		User savedUser = new User();
		savedUser.setFirst_name("John");
		savedUser.setLast_name("Doe");
		savedUser.setEmail("johnny@elevysi.com");
		savedUser.setUsername("johnnie");
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		savedUser.setPassword(encoder.encode("passHere"));
		
		when(this.userDAO.save(this.user)).thenReturn(savedUser);
		User serviceSavedUser = userService.saveUser(this.user);
		
		assertEquals(user.getUsername(), serviceSavedUser.getUsername());
		assertEquals(user.getFirst_name(), serviceSavedUser.getFirst_name());
		assertEquals(user.getLast_name(), serviceSavedUser.getLast_name());
		assertEquals(user.getEmail(), serviceSavedUser.getEmail());
		assertTrue(encoder.matches("passHere", savedUser.getPassword()));
	}

}
