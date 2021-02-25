package com.sozieAjax.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration

@EnableWebSecurity

public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	javax.sql.DataSource dataSource;
	@Autowired 
	private EmployeeAuthenticationSuccessHandler successHandler;
	  
	  
	  // Enable jdbc authentication
	  
	  @Autowired public void configAuthentication(AuthenticationManagerBuilder
	  auth) throws Exception { auth.jdbcAuthentication().dataSource(dataSource); }
	  
	  
	  
	  @Override public void configure(WebSecurity web) throws Exception {
	  web.ignoring().antMatchers("/addRole/**"); }
	 



	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.csrf().disable()
		.authorizeRequests()
				
		//.antMatchers("/", "/csrf", "/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html", "/webjars/**")
         .antMatchers("/v2/api-docs", "/configuration/ui", "/swagger-resources/**", "/configuration/security/**", "/swagger*/**", "/webjars/**")
        .permitAll()
		//.antMatchers("/createUser").permitAll()
		//.antMatchers("/createRole").permitAll()
				  .antMatchers("/").permitAll()
				  
				 
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll()
				.successHandler(successHandler)
				.failureUrl("/403").and().logout().invalidateHttpSession(true).logoutUrl("/logout").logoutSuccessUrl("/").permitAll();
	}
}
