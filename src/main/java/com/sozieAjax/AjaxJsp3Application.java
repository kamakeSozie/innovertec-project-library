package com.sozieAjax;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class AjaxJsp3Application {

	public static void main(String[] args) {
		SpringApplication.run(AjaxJsp3Application.class, args);
	}

	@Bean
	public BCryptPasswordEncoder getBCPC() {
		return new BCryptPasswordEncoder();
	}
}
