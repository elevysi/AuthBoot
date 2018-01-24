package com.elevysi.site.auth.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception{
		return super.authenticationManagerBean();
	}
	
	@Override
	@Bean
	public UserDetailsService userDetailsServiceBean() throws Exception{
		return super.userDetailsServiceBean();
	}
	
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.inMemoryAuthentication()
		.withUser("elevysi_user")
		.password("elevysi")
		.roles("USER")
		.and()
		.withUser("admin")
		.password("elevysi")
		.roles("USER", "ADMIN");
	}
	
	@Override
	public void configure(HttpSecurity http) throws Exception{
		http
		.authorizeRequests()
//			.antMatchers("/oauth/token").permitAll()
			.anyRequest().authenticated()
		.and()
		.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf()
			.disable();
		
//		http
//        .csrf().disable()
//        .anonymous().disable()
//        .authorizeRequests()
//        .antMatchers("/oauth/token").permitAll();
		
	}
	
	@Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

}
