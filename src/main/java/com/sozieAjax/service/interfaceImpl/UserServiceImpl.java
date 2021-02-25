package com.sozieAjax.service.interfaceImpl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.sozieAjax.dto.UserDTO;
import com.sozieAjax.entity.Authorities;
import com.sozieAjax.entity.ProjetEntity;
import com.sozieAjax.entity.UserEntity;
import com.sozieAjax.model.response.ErrorMessage;
import com.sozieAjax.model.response.ErrorMessages;
import com.sozieAjax.repository.AuthoritiesRepository;
import com.sozieAjax.repository.ProjetRepository;
import com.sozieAjax.repository.UserRepository;
import com.sozieAjax.security.UserServiceException;
import com.sozieAjax.service.interfaces.UserService;
import com.sozieAjax.utils.Utils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepository;
	@Autowired
	ProjetRepository projetRepository;
	@Autowired
	AuthoritiesRepository authoritiesRepository;
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	Utils utils;
	
	@Override
	public UserDTO createUser(UserDTO userDTO) {
		ModelMapper mapper = new ModelMapper();
		UserEntity userEntity = mapper.map(userDTO, UserEntity.class);
		
		UserEntity e = userRepository.findByName(userDTO.getName());
		if(e != null){
			throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		}
		
		
		String hashpassword=bCryptPasswordEncoder.encode(userEntity.getPassword());
		userEntity.setUserId(utils.generateUserId(30));
		userEntity.setPassword(hashpassword);
		userEntity.setEnabled(true);
		userEntity.setUsername(userDTO.getName());
		UserEntity userSaved = userRepository.save(userEntity);
		
		if(userSaved.getType() != "USER") {
			Authorities authorities = new Authorities();
		authorities.setUsername(userSaved.getName());
		Set<UserEntity> hset = new HashSet<>();
		hset.add(userSaved);
		authorities.setUsername(userDTO.getName());
		authorities.setAuthority("ROLE_ADMIN");
		authorities.setUserEntities(hset);
		authoritiesRepository.save(authorities);
		}
		
		if(userSaved.getType() != "ADMIN") {
			Authorities authorities = new Authorities();
		authorities.setUsername(userSaved.getName());
		Set<UserEntity> hset = new HashSet<>();
		hset.add(userSaved);
		authorities.setUsername(userDTO.getName());
		authorities.setAuthority("ROLE_USER");
		authorities.setUserEntities(hset);
		authoritiesRepository.save(authorities);
		}
		
		
		
		UserDTO userSavedDTO = mapper.map(userSaved, UserDTO.class);
		
		return userSavedDTO;
	}

	@Override
	public List<UserDTO> listUser() {
		List<UserEntity> list = userRepository.findAll();
		
		List<UserDTO> userDTOs = utils.mapList(list, UserDTO.class);
		return userDTOs;
	}

	@Override
	public List<UserDTO> listUserByProjet(String projetName) {
		ProjetEntity projetEntity = projetRepository.findByProjetId(projetName);
		List<UserEntity> list = userRepository.findByProjetEntities(projetEntity); 
		
		List<UserDTO> userDTOs = utils.mapList(list, UserDTO.class);
		return userDTOs;
	}

}
