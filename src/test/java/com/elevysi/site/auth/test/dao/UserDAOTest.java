package com.elevysi.site.auth.test.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.elevysi.site.auth.AuthBootApplication;
import com.elevysi.site.auth.config.AppConfig;
import com.elevysi.site.auth.dao.UserDAO;
import com.elevysi.site.auth.entity.User;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes={AuthBootApplication.class, AppConfig.class})
@Transactional
public class UserDAOTest {
	
	@Autowired
	private UserDAO userDAO;
	private User user;
	
	@Before
	public void init(){
		this.user = new User();
		user.setFirst_name("John");
		user.setLast_name("Doe");
		user.setEmail("johnny@elevysi.com");
		user.setUsername("johnnie");
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		user.setPassword(encoder.encode("passHere"));
	}
	
	@Test
	public void testSaveUser(){
		User savedUser = userDAO.save(this.user);
		assertEquals(user.getUsername(), savedUser.getUsername());
		assertEquals(user.getFirst_name(), savedUser.getFirst_name());
		assertEquals(user.getLast_name(), savedUser.getLast_name());
		assertEquals(user.getEmail(), savedUser.getEmail());
		
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		assertTrue(encoder.matches("passHere", savedUser.getPassword()));
		
	}
}
