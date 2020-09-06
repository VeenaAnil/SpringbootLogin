package com.staxter.uam.ui.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.staxter.uam.dto.User;
import com.staxter.uam.ui.model.request.UserRegistrationRequest;
import com.staxter.uam.ui.model.response.UserRegistrationResponse;
import com.staxter.uam.ui.service.UserRepository;
/*
 * The Controller class for User Management
 * 
 * @Author : Veena Anil
 * 
 * */

@RestController
@RequestMapping("/userservice")
public class UserController {

	Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	UserRepository userRepository;

	/**
	 * This post method get invoked when new user need to be created
	 *
	 * * @param userDetails //RequestBody
	 * 
	 * @return ResponseEntity<UserRegistrationResponse>
	 */
	@PostMapping("/register")
	public ResponseEntity<UserRegistrationResponse> createUser(
			@Valid @RequestBody UserRegistrationRequest userDetails) {
		
			logger.info("The createUser Method invoked to create a new User");
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
		User user = modelMapper.map(userDetails, User.class);
		user.setPlainTextPassword(userDetails.getPassword());

		User createdUser = userRepository.createUser(user);
		UserRegistrationResponse userRegistrationResponse = modelMapper.map(createdUser,
				UserRegistrationResponse.class);
	
			logger.info("User " + userRegistrationResponse.getFirstName() + " "
					+ userRegistrationResponse.getLastName() + " created successfully");
				return ResponseEntity.status(HttpStatus.CREATED).body(userRegistrationResponse);

	}

}
