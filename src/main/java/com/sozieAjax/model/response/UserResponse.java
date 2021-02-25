package com.sozieAjax.model.response;

import java.util.List;
import java.util.Set;


public class UserResponse {

	private Long id;
	
	private String userId;
	
	private String name;
	
	private String email;
	
	private String username;
	
	private String password;
	
	private Set<RoleResponse> roleEntity;
	
	private List<ProjetResponse> projetEntities;

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

	public Set<RoleResponse> getRoleEntity() {
		return roleEntity;
	}

	public void setRoleEntity(Set<RoleResponse> roleEntity) {
		this.roleEntity = roleEntity;
	}

	public List<ProjetResponse> getProjetEntities() {
		return projetEntities;
	}

	public void setProjetEntities(List<ProjetResponse> projetEntities) {
		this.projetEntities = projetEntities;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
