package org.springframework.security.jespa;

import java.util.Map;

import jespa.ntlm.NtlmResponse;
import jespa.ntlm.NtlmSecurityProvider;
import jespa.security.SecurityProviderException;

public class AuthenticationOnlySecurityProvider extends NtlmSecurityProvider {

	public AuthenticationOnlySecurityProvider(Map properties) {
		super(properties);
	}

	public void authenticate(Object credential) throws SecurityProviderException {
		if (!(credential instanceof NtlmResponse)) {
			throw new SecurityProviderException(0, "Unsupported credential type");
		}
	}
}
