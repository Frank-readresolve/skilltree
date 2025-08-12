package tech.readresolve.skilltree.config;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

public final class SecurityHelper {

	private static final SecurityHelper INSTANCE = new SecurityHelper();

	private JwtProvider jwt;

	private PasswordEncoder encoder;

	private SecurityHelper() {
		//
	}

	public static SecurityHelper getInstance() {
		return INSTANCE;
	}

	void init(JwtProvider jwt, PasswordEncoder encoder) {
		this.jwt = jwt;
		this.encoder = encoder;
	}

	public String createToken(String subject, List<String> roles) {
		return jwt.create(subject, roles);
	}

	public String encode(String rawPassword) {
		return encoder.encode(rawPassword);
	}

	public boolean matches(String rawPassword, String encodedPassword) {
		return encoder.matches(rawPassword, encodedPassword);
	}

	public static SecurityContext securityContext() {
		return SecurityContextHolder.getContext();
	}

	public Authentication authentication() {
		return securityContext().getAuthentication();
	}

	public String principal() {
		return authentication().getName();
	}

}
