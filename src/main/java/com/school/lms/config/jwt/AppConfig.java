package com.school.lms.config.jwt;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/* If we want to store user details in memory instead of database then  
this is how we create it.*/

@Configuration
public class AppConfig {
	
	@Bean
	public UserDetailsService userDetServ(PasswordEncoder paswdEncode) {
		
		UserDetails admin = User.builder().username("Admin").password(paswdEncode.encode("stellar@f")).roles("ADMIN").build();
		UserDetails teacher = User.builder().username("Teacher").password(paswdEncode.encode("teacher@#34")).roles("TEACHER").build();
		UserDetails student = User.builder().username("Student").password(paswdEncode.encode("student@!75")).roles("STUDENT").build();
		UserDetails parent = User.builder().username("Parent").password(paswdEncode.encode("parent@$09")).roles("PARENT").build();

		
		return new InMemoryUserDetailsManager(admin, teacher, student, parent);
	}
	
	@Bean
	public PasswordEncoder paswdEncode() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception{
		return http.getSharedObject(AuthenticationManagerBuilder.class)
				.userDetailsService(userDetServ(paswdEncode()))
				.passwordEncoder(paswdEncode())
				.and()
				.build()
				;
	}
}
