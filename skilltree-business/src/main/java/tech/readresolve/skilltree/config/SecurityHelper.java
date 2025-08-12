package tech.readresolve.skilltree.config;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

public record SecurityHelper(JwtProvider jwt, PasswordEncoder encoder) {

	public String createToken(String subject, List<String> roles) {
		return jwt.create(subject, roles);
	}

	public String encode(String rawPassword) {
		return encoder.encode(rawPassword);
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}

	@SuppressWarnings("static-method")
	public SecurityContext securityContext() {
		return SecurityContextHolder.getContext();
	}

	public Authentication authentication() {
		return securityContext().getAuthentication();
	}

	public String principal() {
		return authentication().getName();
	}

}
