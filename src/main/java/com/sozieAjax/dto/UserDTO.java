package com.sozieAjax.dto;

import java.util.List;
import java.util.Set;


public class UserDTO {

	private Long id;
	
	private String userId;
	
	private String name;
	
	private String email;
	
	private String username;
	
	private Boolean enabled;
	
	private String password;
	
	private String type;
	
	private Set<RoleDTO> roleEntity;
	
	private List<ProjetDTO> projetEntities;
	
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

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

	public Set<RoleDTO> getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(Set<RoleDTO> roleEntity) {
		this.roleEntity = roleEntity;
	}

	public List<ProjetDTO> getProjetEntities() {
		return projetEntities;
	}

	public void setProjetEntities(List<ProjetDTO> projetEntities) {
		this.projetEntities = projetEntities;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
	
}
