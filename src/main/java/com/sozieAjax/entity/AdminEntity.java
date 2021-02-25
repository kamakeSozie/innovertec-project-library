package com.sozieAjax.entity;


import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

public class AdminEntity{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 494229902324805155L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false , length = 50)
	private String name;
	
	@Column(nullable = false , length = 50)
	private String email;
	
	@ManyToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "users_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "roles_id", referencedColumnName = "id") })
	private Set<RoleEntity> roleEntity;
	
	@Column(nullable = false , length = 30)
	private String adminId;
	
	private String description;
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
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
	public Set<RoleEntity> getRoleEntity() {
		return roleEntity;
	}
	public void setRoleEntity(Set<RoleEntity> roleEntity) {
		this.roleEntity = roleEntity;
	}
	
	

}
