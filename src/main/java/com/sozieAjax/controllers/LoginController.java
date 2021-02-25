package com.sozieAjax.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class LoginController {

	//@RequestMapping("/login")
	  public String login() {
		  return "thymeleaf/login"; 
	}
	  
	  @GetMapping(value = "/403")
	  public ModelAndView error() {
		  ModelAndView mv = new ModelAndView();
		  
		  mv.setViewName("jsp/403");
		  mv.addObject("error" , "Mot de passe ou nom incorrecte");
		  return mv; 
	}
	  
	  //@GetMapping(value = "/")
	  public ModelAndView accueil() {
		  ModelAndView mv = new ModelAndView();
		  mv.setViewName("jsp/accueil");
		  return mv; 
	}
}
