package com.sozieAjax.model.response;


public class ProjetResponse {

	private Long id;
	
	private String projetId;
	
	private String projetName;
	
	private String description;

	public String getProjetId() {
		return projetId;
	}

	public void setProjetId(String projetId) {
		this.projetId = projetId;
	}

	public String getProjetName() {
		return projetName;
	}

	public void setProjetName(String projetName) {
		this.projetName = projetName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
