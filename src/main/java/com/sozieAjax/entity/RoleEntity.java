package com.sozieAjax.entity;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "roles")
public class RoleEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2906221430789177526L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false , length = 30)
	private String roleId;
	
	@Column(nullable = false , length = 50)
	private String name;
	
	@ManyToMany(mappedBy = "roleEntity")
	private Set<UserEntity> userEntities;
	
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
	public Set<UserEntity> getUserEntities() {
		return userEntities;
	}
	public void setUserEntities(Set<UserEntity> userEntities) {
		this.userEntities = userEntities;
	}
	

}
