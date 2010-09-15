/**
 * AuthenticationOnlySecurityProvider
 * Project: jespa-spring
 * Licence: Apache License, Version 2.0
 * @author Tomek Kuprowski
 */
package org.springframework.security.jespa;

import java.util.Map;

import jespa.ntlm.NtlmResponse;
import jespa.ntlm.NtlmSecurityProvider;
import jespa.security.SecurityProviderException;

/**
 * Jespa simple security provider which only validates credential types
 */
public class AuthenticationOnlySecurityProvider extends NtlmSecurityProvider {

	/**
	 * Instantiates a new security provider.
	 * 
	 * @param properties
	 *            the properties
	 */
	public AuthenticationOnlySecurityProvider(Map properties) {
		super(properties);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see jespa.ntlm.NtlmSecurityProvider#authenticate(java.lang.Object)
	 */
	public void authenticate(Object credential) throws SecurityProviderException {
		if (!(credential instanceof NtlmResponse)) {
			throw new SecurityProviderException(0, "Unsupported credential type");
		}
	}
}
