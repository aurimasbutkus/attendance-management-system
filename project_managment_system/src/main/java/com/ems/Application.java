package com.ems;

import org.apache.catalina.security.SecurityConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

@SpringBootApplication
public class Application{

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}