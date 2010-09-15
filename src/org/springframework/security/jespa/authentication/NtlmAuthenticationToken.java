/**
 * NtlmAuthenticationToken
 * 
 * Project: jespa-spring
 * Licence: Apache License, Version 2.0
 * @author Tomek Kuprowski
 */
package org.springframework.security.jespa.authentication;

import java.security.Principal;
import java.util.Arrays;
import java.util.Collection;

import jespa.security.SecurityPrincipal;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

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

	/**
	 * Instantiates a new ntlm authentication token.
	 *
	 * @param principal the principal
	 * @param credentials the credentials
	 * @param authorities the authorities
	 */
	public NtlmAuthenticationToken(Principal principal, Object credentials,
			Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	/**
	 * Instantiates a new ntlm authentication token.
	 *
	 * @param principal the principal
	 * @param credentials the credentials
	 * @param authorities the authorities
	 */
	public NtlmAuthenticationToken(Principal principal, Object credentials, GrantedAuthority[] authorities) {
		this(principal, credentials, Arrays.asList(authorities));
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
