package com.sozieAjax.model.response;

import java.util.List;

public class EmployeResponse extends UserResponse{

	private String adminId;
	
	private String description;
	
	private List<ProjetResponse> projetEntities;

	public String getAdminId() {
		return adminId;
	}

	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<ProjetResponse> getProjetEntities() {
		return projetEntities;
	}

	public void setProjetEntities(List<ProjetResponse> projetEntities) {
		this.projetEntities = projetEntities;
	}
	
	
}
