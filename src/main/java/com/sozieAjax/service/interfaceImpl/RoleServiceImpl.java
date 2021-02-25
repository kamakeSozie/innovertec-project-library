package com.sozieAjax.service.interfaceImpl;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sozieAjax.dto.RoleDTO;
import com.sozieAjax.entity.RoleEntity;
import com.sozieAjax.model.response.ErrorMessages;
import com.sozieAjax.repository.RoleRepository;
import com.sozieAjax.security.UserServiceException;
import com.sozieAjax.service.interfaces.RoleService;
import com.sozieAjax.utils.Utils;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;
	@Autowired
	Utils utils;
	
	ModelMapper mapper = new ModelMapper();
	
	@Override
	public RoleDTO createRole(RoleDTO roleDTO) {
		
		RoleEntity r = roleRepository.findByName(roleDTO.getName());
		
		if(r != null){
			throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		}
		
		RoleEntity roleEntity = mapper.map(roleDTO, RoleEntity.class);
		roleEntity.setRoleId(utils.generateUserId(30));
		RoleEntity roleSave = roleRepository.save(roleEntity);
		
		RoleDTO roleSaveValue = mapper.map(roleSave, RoleDTO.class);
		
		return roleSaveValue;
	}

	@Override
	public List<RoleDTO> allRole() {
		List<RoleEntity> roleEntities = roleRepository.findAll();
		
		List<RoleDTO> roleDTOs = utils.mapList(roleEntities, RoleDTO.class);
		return roleDTOs;
	}

}
