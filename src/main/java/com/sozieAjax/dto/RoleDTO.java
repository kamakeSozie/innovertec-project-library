package com.sozieAjax.dto;

import java.util.Set;


public class RoleDTO {

	private Long id;
	
	private String roleId;
	
	private String name;
	
	private Set<UserDTO> userEntities;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<UserDTO> getUserEntities() {
		return userEntities;
	}

	public void setUserEntities(Set<UserDTO> userEntities) {
		this.userEntities = userEntities;
	}
	
	
}
