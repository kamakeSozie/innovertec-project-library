package com.sozieAjax.controllers;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sozieAjax.dto.RoleDTO;
import com.sozieAjax.model.request.RoleRequest;
import com.sozieAjax.model.response.ErrorMessages;
import com.sozieAjax.model.response.RoleResponse;
import com.sozieAjax.security.UserServiceException;
import com.sozieAjax.service.interfaces.RoleService;
import com.sozieAjax.utils.Utils;

@RestController
public class RoleController {
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	Utils utils;
	
	ModelMapper mapper = new ModelMapper();

	@PostMapping(value = "/createRole")
	public ModelAndView createRole(@RequestBody RoleRequest roleRequest) throws Exception{
		
		RoleResponse returnValue = new RoleResponse();
		
		if(roleRequest.getName().isEmpty()) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		
		RoleDTO roleDTO = mapper.map(roleRequest, RoleDTO.class);
		RoleDTO roleCreated = roleService.createRole(roleDTO);
		
		returnValue = mapper.map(roleCreated, RoleResponse.class);

		ModelAndView mv = new ModelAndView();
		mv.addObject("returnValue", returnValue);
		mv.setViewName("jsp/index");
		return mv;
	}
	
	@GetMapping(value = "/roleList") 
	  public List<RoleResponse> roleList() {
		  List<RoleDTO> roleDTOs = roleService.allRole();
		  List<RoleResponse> roleResponses = utils.mapList(roleDTOs, RoleResponse.class);
	  
		  return roleResponses; 
	  }
	
	@PreAuthorize("hasRole('2')")
	@GetMapping(value = "/addRole")
	  public ModelAndView roleSave() {
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("jsp/addRole");
		  return mv;
	  }
}
