package com.example.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
//	 @NotNull(message = "Name Cannot Be Null")
	private String username;
	private String password;

	@NotEmpty(message = "Email Cannot Be Null")
	private String email;
	private String number;

	private boolean enabled;
	private String role;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public User(long id, String username, String password, String email, String number, boolean enabled, String role) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
		this.number = number;
		this.enabled = enabled;
		this.role = role;
	}

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + ", number="
				+ number + ", enabled=" + enabled + ", role=" + role + "]";
	}
	
//	888888888888888888888888888888888888888888888888888888888888

	/*
	 * private Set<Role> roles = new HashSet<>();
	 * 
	 * public boolean hasRole(String roleName) { Iterator<Role> iterator =
	 * this.roles.iterator(); while (iterator.hasNext()) { Role role =
	 * iterator.next(); if (role.getRoleName().equals(roleName)) { return true; } }
	 * 
	 * return false; }
	 */
	 


}
