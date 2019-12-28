package com.mbarcina.springmarket.repository.impl;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IUserDAO;
import com.mbarcina.springmarket.entity.Role;
import com.mbarcina.springmarket.entity.User;
import com.mbarcina.springmarket.repository.IRoleService;
import com.mbarcina.springmarket.repository.IUserService;

@Repository
public class UserServiceImpl implements IUserService {
	
	@Autowired
	private IUserDAO userDAO;
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private IRoleService roleService;

	
	public User findUserById(int pId) {
		return userDAO.findUserById(pId);
	}
	

	public User findUserByEmail(String pEmail) {
		return userDAO.findUserByEmail(pEmail);
	}
	
	public void saveUser(User pUser) {
		pUser.setPassword(bCryptPasswordEncoder.encode(pUser.getPassword()));
        Role userRole = roleService.findByRole("CUSTOMER");
        pUser.setRoles(new ArrayList<Role>(Arrays.asList(userRole)));
		userDAO.saveUser(pUser);
	}

}
