package com.staxter.uam.ui.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.staxter.uam.dto.User;
import com.staxter.uam.exception.UserAlreadyExistsException;

public interface UserRepository extends UserDetailsService{
 User createUser (User user) throws UserAlreadyExistsException;
 User getUserDetailsbyUserName(String userName);
}
