package com.mbarcina.springmarket.repository;

import com.mbarcina.springmarket.entity.Role;

public interface IRoleService {
	public Role findByRole(String role);
}
