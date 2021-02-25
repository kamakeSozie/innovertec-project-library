package com.sozieAjax.dto;

import java.util.List;
import java.util.Set;

public class EmployeDTO{

	private Long id;
	
	private String name;
	
	private String email;
	
	private Set<RoleDTO> roleEntity;
	
	private String employeId;
	
	private String description;
	
	private List<ProjetDTO> projetEntities;

	public String getEmployeId() {
		return employeId;
	}

	public void setEmployeId(String employeId) {
		this.employeId = employeId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProjetDTO> getProjetEntities() {
		return projetEntities;
	}

	public void setProjetEntities(List<ProjetDTO> projetEntities) {
		this.projetEntities = projetEntities;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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
	
	
}
