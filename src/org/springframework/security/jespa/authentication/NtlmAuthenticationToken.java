/**
 * NtlmAuthenticationToken
 * 
 * Project: jespa-spring2
 * Licence: Apache License, Version 2.0
 * @author Tomek Kuprowski
 * Changes:
 * Lydon Chandra 18-October-2010 Springify to spring-security-2.0.x 
 */
package org.springframework.security.jespa.authentication;

import java.security.Principal;

import jespa.security.SecurityPrincipal;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.providers.UsernamePasswordAuthenticationToken;

/**
 * Authentication token which stores only principal (domain winlogin)
 */
public class NtlmAuthenticationToken extends UsernamePasswordAuthenticationToken {

	/** The principal. */
	private SecurityPrincipal principal;

	/**
	 * Instantiates a new ntlm authentication token.
	 *
	 * @param principal the principal
	 */
	public NtlmAuthenticationToken(Principal principal) {
		this(principal, null);
	}

	/**
	 * Instantiates a new ntlm authentication token.
	 *
	 * @param principal the principal
	 * @param credentials the credentials
	 */
	public NtlmAuthenticationToken(Principal principal, Object credentials) {
		super(principal, null);
		setPrincipal(principal);
		setAuthenticated(false);
	}

//	/**
//	 * Instantiates a new ntlm authentication token.
//	 *
//	 * @param principal the principal
//	 * @param credentials the credentials
//	 * @param authorities the authorities
//	 */
//	public NtlmAuthenticationToken(Principal principal, Object credentials,
//			List authorities) {
//		
//		super(principal, credentials, authorities);
//	}

	/**
	 * Instantiates a new ntlm authentication token.
	 *
	 * @param principal the principal
	 * @param credentials the credentials
	 * @param authorities the authorities
	 */
	public NtlmAuthenticationToken(Principal principal, Object credentials, GrantedAuthority[] authorities) {
		super(principal, credentials, authorities);
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.UsernamePasswordAuthenticationToken#getCredentials()
	 */
	@Override
	public Object getCredentials() {
		return null;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.authentication.UsernamePasswordAuthenticationToken#getPrincipal()
	 */
	@Override
	public Object getPrincipal() {
		return principal;
	}

	/**
	 * Sets the principal.
	 *
	 * @param principal the new principal
	 */
	private void setPrincipal(Principal principal) {
		this.principal = (SecurityPrincipal) principal;
	}

}
