package com.staxter.uam.ui.service;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.staxter.uam.dto.User;

public interface UserRepository extends UserDetailsService{
 User createUser (User user);
 User getUserDetailsbyUserName(String userName);
}
