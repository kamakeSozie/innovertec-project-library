package com.sozieAjax.model.request;

public class AdminRequest extends UserRequest{

	private String description;
	
	private String role;

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
