package com.staxter.uam;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class UserAccessModuleApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAccessModuleApplication.class, args);
	}

	/*
	 * Bean to have the BCryptPasswordEncoder object available in the application
	 * context and inject in the UserService Impl via constructor
	 * 
	 */
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder9() {
		return new BCryptPasswordEncoder();
	}
}
