package com.sozieAjax.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sozieAjax.dto.ProjetDTO;
import com.sozieAjax.dto.UserDTO;
import com.sozieAjax.entity.ProjetEntity;
import com.sozieAjax.model.request.ProjetRequest;
import com.sozieAjax.model.response.ErrorMessages;
import com.sozieAjax.model.response.ProjetResponse;
import com.sozieAjax.model.response.UserResponse;
import com.sozieAjax.repository.ProjetRepository;
import com.sozieAjax.security.UserServiceException;
import com.sozieAjax.service.interfaces.ProjetService;
import com.sozieAjax.utils.Utils;

@RestController
public class ProjetController {

	@Autowired
	ProjetService projetService;
	@Autowired
	ProjetRepository projetRepository;
	@Autowired
	Utils utils;
	
	@PostMapping(value = "/createProjet")
	public ProjetResponse createProjet (@RequestBody ProjetRequest projetRequest) throws Exception {
		
		ProjetResponse returnValue = new ProjetResponse();
		
		if(projetRequest.getProjetName().isEmpty() || projetRequest.getDescription().isEmpty()) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		ModelMapper mapper = new ModelMapper();
		
		ProjetDTO projetDTO = mapper.map(projetRequest, ProjetDTO.class);
		ProjetDTO projetSaved = projetService.createProjet(projetDTO);
		
		returnValue = mapper.map(projetSaved, ProjetResponse.class);
		return returnValue;
	}
	
	@PostMapping(value = "/userToProjet")
	public UserResponse userToProjet ( String userId , String projetid) throws Exception {
		UserResponse returnValue = new UserResponse();
		ModelMapper mapper = new ModelMapper();
		
		if(projetid.isEmpty() || userId.isEmpty() || userId.equals("") || projetid.equals("")) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		UserDTO projetSaved = projetService.addUserToProjet(userId, projetid);
		
		returnValue = mapper.map(projetSaved, UserResponse.class);
		return returnValue;
	}
	
	@GetMapping(value = "/projetList")
	public List<ProjetResponse> projetList() {
		List<ProjetEntity> listProjet = projetRepository.findAll();
		
		ModelMapper modelMapper = new ModelMapper();
        List<ProjetResponse> projetResponsesList = listProjet
                .stream()
                .map(projetResponses -> modelMapper.map(projetResponses, ProjetResponse.class))
                .collect(Collectors.toList());
		return projetResponsesList;
	}
	
	@PostMapping(value = "/activer")
	public ProjetResponse activedProjet(String projetId) {
		ProjetDTO projetDTO = projetService.activedProjet(projetId);
		ModelMapper modelMapper = new ModelMapper();
		
		ProjetResponse projetResponse = modelMapper.map(projetDTO, ProjetResponse.class);
		return projetResponse;
	}
	
	@PostMapping(value = "/desactiver")
	public ProjetResponse desactivedProjet(String projetId) {
		ProjetDTO projetDTO = projetService.desactivedProjet(projetId);
		ModelMapper modelMapper = new ModelMapper();
		
		ProjetResponse projetResponse = modelMapper.map(projetDTO, ProjetResponse.class);
		return projetResponse;
	}
	
	@GetMapping(value = "/")
	public ModelAndView allProjetActive() {
		
		List<ProjetDTO> projetDTOs = projetService.projetByAcive();
		List<ProjetResponse> returnValue = utils.mapList(projetDTOs, ProjetResponse.class);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("jsp/accueil");
		mv.addObject("allProjetActive", returnValue);
		return mv;
	}
}
