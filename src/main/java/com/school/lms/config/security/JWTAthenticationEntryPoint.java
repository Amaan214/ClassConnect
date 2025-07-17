package com.school.lms.config.security;

import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
// AuthenticationEntryPoint is responsible for defining what happens when an unauthenticated user tries to access a secured resource
public class JWTAthenticationEntryPoint implements AuthenticationEntryPoint {

	/*
	 * request: The HTTP request that triggered the authentication exception.
	 * response: The HTTP response that will be sent back to the client.
	 * authException: The exception indicating why authentication failed (e.g., missing or invalid credentials).
	 */
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {

		response.setStatus(HttpServletResponse.SC_UNAUTHORIZED); // Sets HTTP status code to 401(Unauthorized)
		PrintWriter write = response.getWriter();
		
		write.println("Access Denied !! " + authException.getMessage());
	}

}
