package com.mbarcina.springmarket.dao;

import com.mbarcina.springmarket.entity.User;

public interface IUserDAO {
	
	public User findUserById(int pId);
	public User findUserByEmail(String pEmail);
	public void saveUser(User pUser);
}
