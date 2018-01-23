package com.elevysi.site.auth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@Configuration
public class ServiceConfig {
	
	@Value("${sigining.key}")
	private String jwtSiginingKey="";

	public String getJwtSiginingKey() {
		return jwtSiginingKey;
	}

	
	 
}
