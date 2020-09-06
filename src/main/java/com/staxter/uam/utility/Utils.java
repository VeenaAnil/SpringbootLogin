package com.staxter.uam.utility;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.staxter.uam.dto.User;

@Service
public class Utils {

	
	public String generateUserId() {
	 return UUID.randomUUID().toString();
	}
	
}
