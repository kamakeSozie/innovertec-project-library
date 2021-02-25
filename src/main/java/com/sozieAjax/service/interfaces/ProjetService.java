package com.sozieAjax.service.interfaces;

import java.util.List;

import com.sozieAjax.dto.ProjetDTO;
import com.sozieAjax.dto.UserDTO;

public interface ProjetService {

	ProjetDTO createProjet(ProjetDTO projetDTO);
	
	UserDTO addUserToProjet(String userid , String projetid);
	
	List<ProjetDTO> listProjet();
	List<ProjetDTO> listProjetByEmploye(String projetName);
	
	List<ProjetDTO> listByProjetName(String projetName);
	
	ProjetDTO activedProjet(String projetId);
	ProjetDTO desactivedProjet(String projetId);
	
	List<ProjetDTO> projetByAcive();
}
