package com.elevysi.site.auth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@RefreshScope
public class AuthBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthBootApplication.class, args);
	}
}
