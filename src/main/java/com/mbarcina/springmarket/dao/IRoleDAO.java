package com.mbarcina.springmarket.dao;

import com.mbarcina.springmarket.entity.Role;

public interface IRoleDAO {
	public Role findByRole(String role);
}
