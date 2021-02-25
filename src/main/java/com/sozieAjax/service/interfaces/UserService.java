package com.sozieAjax.service.interfaces;

import java.util.List;

import com.sozieAjax.dto.UserDTO;

public interface UserService {

	UserDTO createUser(UserDTO userDTO);
	
	List<UserDTO> listUser();
	List<UserDTO> listUserByProjet(String projetName);
}
