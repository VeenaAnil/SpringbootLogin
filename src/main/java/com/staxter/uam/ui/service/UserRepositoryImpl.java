package com.staxter.uam.ui.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.staxter.uam.dto.User;
import com.staxter.uam.exception.UserAccessException;
import com.staxter.uam.utility.Constants;
import com.staxter.uam.utility.Utils;
/*
 * 
 * This User repository Service Implementation Class
 * 
 * @author: Veena ANil
 * 
 * */
@Service
public class UserRepositoryImpl implements UserRepository {

	// This map stores users temporarily
	Map<String, User> users;
	
	Utils utils;
	//Springsecurity password encoder object
	BCryptPasswordEncoder bCryptPasswordEncoder;
	public UserRepositoryImpl() {
		super();
	}

	//Constructor based dependency is added to inject util to the UserRepositortImpl
	@Autowired
	public UserRepositoryImpl(Utils utils, BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.utils = utils;
		this.bCryptPasswordEncoder=bCryptPasswordEncoder;
	}

	@Override
	/*
	 * The ser
	 * 
	 * */
	
	public User createUser(User user)  {
		if(users==null) users=new HashMap<>();
		
		// User existing = users.findOne(user.getId());
		if(users.containsKey(user.getUserName())) 
			throw new UserAccessException(Constants.USER_ALREADY_EXISTS);
		user.setId(utils.generateUserId()); 
		user.setHashedPassword(bCryptPasswordEncoder.encode(user.getPlainTextPassword()));
		System.out.println(user.getHashedPassword());
		users.put(user.getUserName(), user);
		
		return user;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			
		User user=users.get(username);
		if(user==null) throw new UsernameNotFoundException(username);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getHashedPassword(), true, true, true, true, new ArrayList<>());
		 
	}
	public User getUserDetailsbyUserName(String userName) {
		User user=users.get(userName);
		if(user==null) throw new UsernameNotFoundException(userName);
		return user;
	}
	

}
