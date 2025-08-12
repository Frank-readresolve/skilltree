package tech.readresolve.skilltree.config;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public final class SecurityContextUtil {

	private SecurityContextUtil() {
		// Not instantiable
	}

	public static SecurityContext securityContext() {
		return SecurityContextHolder.getContext();
	}

	public static Authentication authentication() {
		return securityContext().getAuthentication();
	}

	public static String principal() {
		return authentication().getName();
	}

}
