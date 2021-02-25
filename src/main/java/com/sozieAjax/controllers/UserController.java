package com.sozieAjax.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sozieAjax.dto.RoleDTO;
import com.sozieAjax.dto.UserDTO;
import com.sozieAjax.entity.ProjetEntity;
import com.sozieAjax.entity.RoleEntity;
import com.sozieAjax.entity.UserEntity;
import com.sozieAjax.model.request.UserRequest;
import com.sozieAjax.model.response.ErrorMessages;
import com.sozieAjax.model.response.ProjetResponse;
import com.sozieAjax.model.response.RoleResponse;
import com.sozieAjax.model.response.UserResponse;
import com.sozieAjax.repository.ProjetRepository;
import com.sozieAjax.repository.RoleRepository;
import com.sozieAjax.repository.UserRepository;
import com.sozieAjax.security.UserServiceException;
import com.sozieAjax.service.interfaces.RoleService;
import com.sozieAjax.service.interfaces.UserService;
import com.sozieAjax.utils.Utils;

@RestController
public class UserController {

	@Autowired
	UserService userService;
	@Autowired
	RoleRepository roleRepository;
	@Autowired
	ProjetRepository projetRepository;
	@Autowired
	UserRepository userRepository;
	@Autowired
	RoleService roleService;
	@Autowired
	Utils utils;
	
	@PostMapping(value = "/createUser")
	public UserResponse createUser(@Validated @RequestBody UserRequest userRequest) throws Exception {
		
		UserResponse returnValue = new UserResponse();
		ModelMapper mapper = new ModelMapper();
		
		if(userRequest.getName().isEmpty() || userRequest.getEmail().isEmpty() || 
				userRequest.getRoleEntity().isEmpty()) {
			throw new UserServiceException(ErrorMessages.MISSING_REQUIRED_FIELD.getErrorMessage());
		}
		
		RoleEntity roleEntity = roleRepository.findByRoleId(userRequest.getRoleEntity());
		RoleDTO roleDTO = mapper.map(roleEntity, RoleDTO.class);
		Set<RoleDTO> hset = new HashSet<>();
		hset.add(roleDTO);
		UserDTO userDTO = mapper.map(userRequest, UserDTO.class);
		userDTO.setRoleEntity(hset);
		if(roleEntity.getName().equals("ROLE_ADMIN")) {
			userDTO.setType("ADMIN");
		}
		if(roleEntity.getName().equals("ROLE_USER")) {
			userDTO.setType("USER");
		}
		
		UserDTO userSaved = userService.createUser(userDTO);
		
		returnValue = mapper.map(userSaved, UserResponse.class);
		ModelAndView mv = new ModelAndView();
		mv.addObject("returnValue", returnValue);
		mv.setViewName("jsp/index");
		return returnValue;
	}
	
	
	  @GetMapping(value = "/userList") 
	  public ModelAndView userList(HttpServletRequest httpServletRequest) {
		  List<UserDTO> userDTOs = userService.listUser();
		  List<UserResponse> userResponses = utils.mapList(userDTOs, UserResponse.class);
	  
		  HttpSession httpSession=httpServletRequest.getSession();
		  SecurityContext securityContext=(SecurityContext)
		  	httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		  String username=securityContext.getAuthentication().getName();
		  List<String> roles=new ArrayList<>();
		  for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
		  roles.add(ga.getAuthority());
		  }
	       Map<String, Object> params=new HashMap<>();
		  params.put("username", username);
		  params.put("roles", roles);
		  
		  ModelAndView mv = new ModelAndView();
			mv.setViewName("jsp/listUser");
			mv.addObject("username" , username);
			mv.addObject("listEmploye", userResponses);
		  return mv; 
	  }
	  
	  @GetMapping(value = "/addUser")
	  public ModelAndView userSave(HttpServletRequest httpServletRequest) {
		  List<RoleDTO> roleDTOs = roleService.allRole();
		  List<RoleResponse> roleResponses = utils.mapList(roleDTOs, RoleResponse.class);
		  //users list
		  List<UserDTO> userDTOs = userService.listUser();
		  List<UserResponse> userResponses = utils.mapList(userDTOs, UserResponse.class);
		  //project list
		  List<ProjetEntity> listProjet = projetRepository.findAll();
			ModelMapper modelMapper = new ModelMapper();
	        List<ProjetResponse> projetResponsesList = listProjet
	                .stream()
	                .map(projetResponses -> modelMapper.map(projetResponses, ProjetResponse.class))
	                .collect(Collectors.toList());
	        
	        HttpSession httpSession=httpServletRequest.getSession();
			  SecurityContext securityContext=(SecurityContext)
			  	httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
			  String username=securityContext.getAuthentication().getName();
			  List<String> roles=new ArrayList<>();
			  for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
			  roles.add(ga.getAuthority());
			  }
		       Map<String, Object> params=new HashMap<>();
			  params.put("username", username);
			  params.put("roles", roles);
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("jsp/addUser");
		  mv.addObject("username" , username);
		  mv.addObject("listRole", roleResponses);
		  mv.addObject("listEmploye", userResponses);
		  mv.addObject("listProject", projetResponsesList);
		  return mv;
	  }
	  
	  @GetMapping(value = "/userListByProjet")
	  public List<UserResponse> userListByProjet( String projetName) { 
		  List<UserDTO> userDTOs = userService.listUserByProjet(projetName);
		  List<UserResponse> userResponses = utils.mapList(userDTOs, UserResponse.class);
		  //project list
		  List<ProjetEntity> listProjet = projetRepository.findAll();
			ModelMapper modelMapper = new ModelMapper();
	        List<ProjetResponse> projetResponsesList = listProjet
	                .stream()
	                .map(projetResponses -> modelMapper.map(projetResponses, ProjetResponse.class))
	                .collect(Collectors.toList());
	        
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("jsp/listUser");
		  mv.addObject("listEmployeByProjetc", userResponses);
		  mv.addObject("listProject", projetResponsesList);
		  return userResponses;
	  }
	  
	  @GetMapping(value = "/showAllEmplByProject")
		public ModelAndView showAllDpt(HttpServletRequest httpServletRequest) {
		  //project list
		  List<ProjetEntity> listProjet = projetRepository.findAll();
			ModelMapper modelMapper = new ModelMapper();
	        List<ProjetResponse> projetResponsesList = listProjet
	                .stream()
	                .map(projetResponses -> modelMapper.map(projetResponses, ProjetResponse.class))
	                .collect(Collectors.toList());
	        
	        
	        HttpSession httpSession=httpServletRequest.getSession();
			  SecurityContext securityContext=(SecurityContext)
			  	httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
			  String username=securityContext.getAuthentication().getName();
			  List<String> roles=new ArrayList<>();
			  for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
			  roles.add(ga.getAuthority());
			  }
		       Map<String, Object> params=new HashMap<>();
			  params.put("username", username);
			  params.put("roles", roles);
			ModelAndView mv = new ModelAndView();
			mv.addObject("username" , username);
			mv.setViewName("jsp/listUser");
			mv.addObject("listProject", projetResponsesList);
			return mv;
		}
	  
	  @GetMapping(value = "/userPage")
		public ModelAndView userPage(HttpServletRequest httpServletRequest , String nameUser) {
		  //project list
		  List<ProjetEntity> listProjet = projetRepository.findAll();
			ModelMapper modelMapper = new ModelMapper();
	        List<ProjetResponse> projetResponsesList = listProjet
	                .stream()
	                .map(projetResponses -> modelMapper.map(projetResponses, ProjetResponse.class))
	                .collect(Collectors.toList());
	        
			  HttpSession httpSession=httpServletRequest.getSession();
			  SecurityContext securityContext=(SecurityContext)
			  	httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
			  String username=securityContext.getAuthentication().getName();
			  List<String> roles=new ArrayList<>();
			  for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
			  roles.add(ga.getAuthority());
			  }
			  UserEntity userEntity = userRepository.findByName(username);
		       List<ProjetEntity> projetByUser = projetRepository.findByUser(userEntity);
		       List<ProjetResponse> projetByUserRes = utils.mapList(projetByUser, ProjetResponse.class);
			 
		       Map<String, Object> params=new HashMap<>();
			  params.put("username", username);
			  params.put("roles", roles);
			  ModelAndView mv = new ModelAndView();
			  mv.addObject("username" , username);
			mv.setViewName("jsp/userPage");
			mv.addObject("listProject", projetResponsesList);
			mv.addObject("projetByUser" , projetByUserRes);
			return mv;
		}
	  
		
		
		  @GetMapping("/login") 
		  public ModelAndView login() {
			  ModelAndView mv = new ModelAndView(); 
			  mv.setViewName("jsp/login");
			  return mv; 
			 }
		 
		  @GetMapping("/adminPages") 
		  public ModelAndView userLists(HttpServletRequest httpServletRequest) {
			  List<UserDTO> userDTOs = userService.listUser();
			  List<UserResponse> userResponses = utils.mapList(userDTOs, UserResponse.class);
		  
			//project list
			  List<ProjetEntity> listProjet = projetRepository.findAll();
				ModelMapper modelMapper = new ModelMapper();
		        List<ProjetResponse> projetResponsesList = listProjet
		                .stream()
		                .map(projetResponses -> modelMapper.map(projetResponses, ProjetResponse.class))
		                .collect(Collectors.toList());
		        
		        
		        HttpSession httpSession=httpServletRequest.getSession();
				  SecurityContext securityContext=(SecurityContext)
				  	httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
				  String username=securityContext.getAuthentication().getName();
				  List<String> roles=new ArrayList<>();
				  for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
				  roles.add(ga.getAuthority());
				  }
			       Map<String, Object> params=new HashMap<>();
				  params.put("username", username);
				  params.put("roles", roles);
				  
			  ModelAndView mv = new ModelAndView();
				mv.setViewName("jsp/adminPage");
				mv.addObject("listEmploye", userResponses);
				mv.addObject("username" , username);
				mv.addObject("listProject" , projetResponsesList);
			  return mv; 
			 }
	 
		  
		  @GetMapping(value="/getLogedUser")
		  public ModelAndView getLogedUser(HttpServletRequest httpServletRequest){
		  HttpSession httpSession=httpServletRequest.getSession();
		  SecurityContext securityContext=(SecurityContext)
		  	httpSession.getAttribute("SPRING_SECURITY_CONTEXT");
		  String username=securityContext.getAuthentication().getName();
		  List<String> roles=new ArrayList<>();
		  for(GrantedAuthority ga:securityContext.getAuthentication().getAuthorities()){
		  roles.add(ga.getAuthority());
		  }
		  Map<String, Object> params=new HashMap<>();
		  params.put("username", username);
		  params.put("roles", roles);
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("jsp/userPage");
		  mv.addObject("username" , username);
		  return mv;
		  }
}
