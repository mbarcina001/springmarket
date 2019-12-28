package com.mbarcina.springmarket.repository;

import com.mbarcina.springmarket.entity.User;

public interface IUserService {
	
	public User findUserById(int pId);
	public User findUserByEmail(String pEmail);
	public void saveUser(User pUser);

}
