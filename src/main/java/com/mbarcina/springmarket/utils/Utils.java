package com.mbarcina.springmarket.utils;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.mbarcina.springmarket.entity.User;
import com.mbarcina.springmarket.repository.IUserService;

public class Utils {
	
	private static Utils myUtils = new Utils();
	
	private Utils() { }
	
	public static Utils getUtils() {
		return myUtils;
	}
	
	public User getLoggedUser(IUserService userService){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	    String email = auth.getName();
		// get customer from the service
		User user = userService.findUserByEmail(email);
		
		return user;
	}
	
	public void setLoggedUser(User user){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();		
		Authentication newAuth = new UsernamePasswordAuthenticationToken(user.getEmail(), auth.getCredentials(), auth.getAuthorities());
		System.out.println(newAuth);
		SecurityContextHolder.getContext().setAuthentication(newAuth);
	}

}
