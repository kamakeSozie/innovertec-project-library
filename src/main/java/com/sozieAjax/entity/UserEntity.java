package com.sozieAjax.entity;

import java.io.Serializable;
import java.util.List;
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

@Entity
@Table(name = "users")
public class UserEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7056352863842126992L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false , length = 30)
	private String userId;
	
	@Column(nullable = false , length = 50)
	private String name;
	
	@Column(nullable = false , length = 50)
	private String email;
	
	private String username;
	
	private Boolean enabled;
	
	private String password;
	
	private String type;
	
	@ManyToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinTable(name = "users_roles", joinColumns = {
			@JoinColumn(name = "users_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "roles_id", referencedColumnName = "id") })
	private Set<RoleEntity> roleEntity;
	
	@ManyToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinTable(name = "users_projet", joinColumns = {
			@JoinColumn(name = "users_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "projets_id", referencedColumnName = "id") })
	private List<ProjetEntity> projetEntities;
	
	@ManyToMany(cascade = { CascadeType.DETACH }, fetch = FetchType.LAZY)
	@JoinTable(name = "users_authorities", joinColumns = {
			@JoinColumn(name = "users_id", referencedColumnName = "id") }, inverseJoinColumns = {
					@JoinColumn(name = "authorities", referencedColumnName = "idAuth") })
	private Set<Authorities> authorities;
	
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
	public Set<RoleEntity> getRoleEntity() {
		return roleEntity;
	}
	public void setRoleEntity(Set<RoleEntity> roleEntity) {
		this.roleEntity = roleEntity;
	}
	public List<ProjetEntity> getProjetEntities() {
		return projetEntities;
	}
	public void setProjetEntities(List<ProjetEntity> projetEntities) {
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
