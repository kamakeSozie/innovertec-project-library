package com.sozieAjax.model.request;



public class UserRequest {

	private String name;
	
	private String email;
	
	private String password;
	
	private String roleEntity;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(String roleEntity) {
		this.roleEntity = roleEntity;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
