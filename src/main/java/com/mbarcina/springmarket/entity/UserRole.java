package com.mbarcina.springmarket.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="User_Role")
public class UserRole implements Serializable {
	
	private static final long serialVersionUID = -5604135876295317150L;

	@Id
	@ManyToOne
	@JoinColumn
	private User user;
	
	@Id
	@ManyToOne
	@JoinColumn
	private Role role;
	
	public UserRole() { }
	
	public UserRole(User pUser, Role pRole) { 
		this.user = pUser;
		this.role = pRole;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	

}
