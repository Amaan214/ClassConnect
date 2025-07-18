package com.school.lms.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	@Autowired
	private JWTAthenticationEntryPoint point;
	
	@Autowired
	private JWTAuthenticationFilter filter;
	
	@Bean
	public SecurityFilterChain securFilterChain(HttpSecurity http) throws Exception{
//		configuration
		http.csrf(csrf -> csrf.disable())
		.cors(cors -> cors.disable())
		.authorizeHttpRequests(auth -> auth.requestMatchers("/home/**")
				.authenticated()
				.requestMatchers("auth/login").permitAll()
				.anyRequest()
				.authenticated())
		.exceptionHandling(except -> except.authenticationEntryPoint(point))
		.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		;
		
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		return http.build();
	}
}
