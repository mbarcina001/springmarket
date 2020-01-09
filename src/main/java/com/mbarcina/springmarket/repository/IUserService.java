package com.mbarcina.springmarket.repository;

import com.mbarcina.springmarket.entity.User;

public interface IUserService {
	
	public User findUserById(int pId);
	public User findUserByEmail(String pEmail);
	public void createUser(User pUser);
	public void updateUser(User pUser);
	public boolean isPasswordEquals(User pUser, String pPasword);
	public String getPasswordEncoded(String pPlainPassword);
}
