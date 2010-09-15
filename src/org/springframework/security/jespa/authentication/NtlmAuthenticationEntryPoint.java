/**
 * NtlmAuthenticationEntryPoint
 * Project: jespa-spring
 * Licence: Apache License, Version 2.0
 * @author Tomek Kuprowski
 */
package org.springframework.security.jespa.authentication;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * Authentication entry point which just rejects access
 * 
 * @see org.springframework.security.web.AuthenticationEntryPoint
 */
public class NtlmAuthenticationEntryPoint implements AuthenticationEntryPoint {

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.security.web.AuthenticationEntryPoint#commence(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * org.springframework.security.core.AuthenticationException)
	 */
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
	}

}
