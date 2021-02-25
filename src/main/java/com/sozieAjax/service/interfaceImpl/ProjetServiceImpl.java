package com.sozieAjax.service.interfaceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sozieAjax.dto.ProjetDTO;
import com.sozieAjax.dto.UserDTO;
import com.sozieAjax.entity.ProjetEntity;
import com.sozieAjax.entity.UserEntity;
import com.sozieAjax.model.response.ErrorMessages;
import com.sozieAjax.repository.ProjetRepository;
import com.sozieAjax.repository.UserRepository;
import com.sozieAjax.security.UserServiceException;
import com.sozieAjax.service.interfaces.ProjetService;
import com.sozieAjax.utils.Utils;

@Service
public class ProjetServiceImpl implements ProjetService {

	@Autowired
	ProjetRepository projetRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	Utils utils;
	
	ModelMapper mapper = new ModelMapper();
	
	@Override
	public ProjetDTO createProjet(ProjetDTO projetDTO) {
		ProjetEntity p = projetRepository.findByProjetName(projetDTO.getProjetName());
		
		if(p != null){
			throw new UserServiceException(ErrorMessages.RECORD_ALREADY_EXISTS.getErrorMessage());
		}
		
		ModelMapper mapper = new ModelMapper();
		ProjetEntity projetEntity = mapper.map(projetDTO, ProjetEntity.class);
		projetEntity.setProjetId(utils.generateUserId(30));
		
		ProjetEntity projetSaved = projetRepository.save(projetEntity);
		
		ProjetDTO projetSavedDTO = mapper.map(projetSaved, ProjetDTO.class);
		
		return projetSavedDTO;
	}

	@Override
	public UserDTO addUserToProjet(String userid, String projetid) {
		ModelMapper mapper = new ModelMapper();
		UserEntity userEntity = userRepository.findByUserId(userid);
		ProjetEntity projetEntity = projetRepository.findByProjetId(projetid);
		
		userEntity.getProjetEntities().add(projetEntity);
		
		UserEntity userEntity2 = userRepository.save(userEntity);

		UserDTO listProjetDTO = mapper.map(userEntity2, UserDTO.class);
		return listProjetDTO;
	}

	@Override
	public List<ProjetDTO> listProjet() {
		List<ProjetEntity> projetEntities = projetRepository.findAll();
		//List<ProjetDTO> projetDTOs = utils.mapList(projetEntities, ProjetDTO.class);
		
		ModelMapper modelMapper = new ModelMapper();
        List<ProjetDTO> projetDTOList = projetEntities
                .stream()
                .map(projetDTOs -> modelMapper.map(projetDTOs, ProjetDTO.class))
                .collect(Collectors.toList());
		return projetDTOList;
	}

	@Override
	public List<ProjetDTO> listProjetByEmploye(String projetName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProjetDTO> listByProjetName(String projetName) {
		UserEntity userEntity2 = userRepository.findByName(projetName);
		List<ProjetEntity> projetByUser = projetRepository.findByUser(userEntity2);
		
		List<ProjetDTO> projetDTOs = utils.mapList(projetByUser, ProjetDTO.class);
		return projetDTOs;
	}

	@Override
	public ProjetDTO activedProjet(String projetId) {
		ModelMapper mapper = new ModelMapper();
		ProjetEntity projetEntity = projetRepository.findByProjetId(projetId);
		projetEntity.setEnable(true);
		
		ProjetEntity projetEntity2 = projetRepository.save(projetEntity);
		ProjetDTO p = mapper.map(projetEntity2, ProjetDTO.class);
		return p;
	}

	@Override
	public ProjetDTO desactivedProjet(String projetId) {
		ModelMapper mapper = new ModelMapper();
		ProjetEntity projetEntity = projetRepository.findByProjetId(projetId);
		projetEntity.setEnable(false);
		
		ProjetEntity projetEntity2 = projetRepository.save(projetEntity);
		ProjetDTO p = mapper.map(projetEntity2, ProjetDTO.class);
		return p;
	}

	@Override
	public List<ProjetDTO> projetByAcive() {
		List<ProjetEntity> projetEntity = projetRepository.findByEnable(true);
		
		List<ProjetDTO> projetDTOs = utils.mapList(projetEntity, ProjetDTO.class);
		return projetDTOs;
	}

}
