package com.sozieAjax.dto;

import java.util.List;

public class ProjetDTO {

	private long id;
	
	private String projetId;
	
	private String projetName;
	
	private Boolean enable;
	
	private String description;
	
	private List<UserDTO> user;

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

	public List<UserDTO> getUser() {
		return user;
	}

	public void setUser(List<UserDTO> user) {
		this.user = user;
	}

	public Boolean getEnable() {
		return enable;
	}

	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	
	
}
