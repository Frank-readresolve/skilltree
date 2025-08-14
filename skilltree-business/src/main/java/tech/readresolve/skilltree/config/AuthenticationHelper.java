package tech.readresolve.skilltree.config;

import java.util.List;

import org.springframework.security.crypto.password.PasswordEncoder;

public final class AuthenticationHelper {

    private final JwtProvider jwt;

    private final PasswordEncoder encoder;

    AuthenticationHelper(JwtProvider jwt, PasswordEncoder encoder) {
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

}
