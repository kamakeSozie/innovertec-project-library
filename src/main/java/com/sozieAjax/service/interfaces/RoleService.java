package com.sozieAjax.service.interfaces;

import java.util.List;

import com.sozieAjax.dto.RoleDTO;

public interface RoleService {

	RoleDTO createRole(RoleDTO roleDTO);
	
	List<RoleDTO> allRole();
}
