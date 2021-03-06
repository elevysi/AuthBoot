package com.elevysi.site.auth.controller;

import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.elevysi.site.auth.entity.User;
import com.elevysi.site.auth.service.UserService;


@RestController
//@EnableAuthorizationServer
//@EnableResourceServer
public class UserController extends AbstractController{
	
	
	private UserService userService;
	
	@Autowired
	public UserController(UserService userService){
		this.userService = userService;
	}
	
	
	@RequestMapping(value="/user", produces="application/json")
	public Map<String, Object> user(OAuth2Authentication user){
		Map<String, Object> userInfo = new HashMap<>();
		userInfo.put("user", user.getUserAuthentication().getPrincipal());
		userInfo.put("authorities", AuthorityUtils.authorityListToSet(user.getUserAuthentication().getAuthorities()));
		return userInfo;
	}
	
	
	@RequestMapping(value="/add", method=RequestMethod.GET)
	public String add(Model model){
		User user = new User();
		user.setUuid(User.generateStaticUUID());
		model.addAttribute("user", user);
		return "addUser";
	}
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public String doAdd(Model model, @Valid @ModelAttribute("user")User user, BindingResult result, RedirectAttributes redirectAttributes){
//		if(result.hasErrors()){
//			return "addUser";
//		}
		userService.saveUser(user);
		
		return "addUser";
	}

}
