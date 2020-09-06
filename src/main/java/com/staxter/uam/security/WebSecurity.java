package com.staxter.uam.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.staxter.uam.ui.service.UserRepository;

@Configuration
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {
	
	private UserRepository userRepository;
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	private Environment env;
	 public WebSecurity(UserRepository userRepository,BCryptPasswordEncoder bCryptPasswordEncoder,Environment env){
		 
		this.userRepository=userRepository;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
		this.env=env;
	 }

	/*
	 * Overiding the configure method
	 * 
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.csrf().disable();
		http
			.authorizeRequests()
			.antMatchers("*").permitAll()
		.and()
			.addFilter(getAuthenticationFilter());

	}

	/*
	 * Function to return the AuthenticationFilter object
	 * 
	 */
	private AuthenticationFilter getAuthenticationFilter() throws Exception {

		AuthenticationFilter authenticationFilter = new AuthenticationFilter(userRepository,env,authenticationManager());
		//Setting custom User authentication url
		authenticationFilter.setFilterProcessesUrl(env.getProperty("login.url.path"));
		//	authenticationFilter.setAuthenticationManager(authenticationManager());
		return authenticationFilter;
	}
	
	
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userRepository).passwordEncoder(bCryptPasswordEncoder);
	}

	
}
