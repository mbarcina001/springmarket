package com.mbarcina.springmarket.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mbarcina.springmarket.dao.IRoleDAO;
import com.mbarcina.springmarket.entity.Role;
import com.mbarcina.springmarket.repository.IRoleService;

@Repository
public class RoleServiceImpl implements IRoleService {

	@Autowired 
	private IRoleDAO roleDAO;
	
	public Role findByRole(String role) {
		return roleDAO.findByRole(role);
	}

}
