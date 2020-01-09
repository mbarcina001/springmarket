package com.mbarcina.springmarket.repository.impl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IUserDAO;
import com.mbarcina.springmarket.entity.Role;
import com.mbarcina.springmarket.entity.User;
import com.mbarcina.springmarket.entity.UserRole;
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
	
	public void createUser(User pUser) {
		// Enabled by default
		pUser.setEnabled(1);
		
		// Password encrypted
		pUser.setPassword(bCryptPasswordEncoder.encode(pUser.getPassword()));
		
		// Add Customer Role by default
        Role role = roleService.findByRole("CUSTOMER");
        pUser.setRoles(new ArrayList<UserRole>());
        UserRole userRole = new UserRole(pUser, role);
        pUser.addRole(userRole);
        
        // Save user
		userDAO.saveUser(pUser);
	}
	
	public void updateUser(User pUser) {
		userDAO.saveUser(pUser);
	}
	
	public boolean isPasswordEquals(User pUser, String pPasword) {
		return bCryptPasswordEncoder.matches(pPasword, pUser.getPassword());
	}
	
	public String getPasswordEncoded(String pPlainPassword) {
		return bCryptPasswordEncoder.encode(pPlainPassword);
	}

}
