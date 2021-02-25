package com.sozieAjax.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "projets")
public class ProjetEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4188261199902051525L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(nullable = false , length = 30)
	private String projetId;
	
	@Column(nullable = false , length = 30)
	private String projetName;
	
	private Boolean enable;
	
	@Column(nullable = false , columnDefinition="TEXT")
	private String description;
	
	@ManyToMany(mappedBy = "projetEntities")
	private List<UserEntity> user;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getProjetId() {
		return projetId;
	}

	public void setProjetId(String projetId) {
		this.projetId = projetId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getProjetName() {
		return projetName;
	}

	public void setProjetName(String projetName) {
		this.projetName = projetName;
	}

	public List<UserEntity> getUser() {
		return user;
	}

	public void setUser(List<UserEntity> user) {
		this.user = user;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
    
    
	
	
}
