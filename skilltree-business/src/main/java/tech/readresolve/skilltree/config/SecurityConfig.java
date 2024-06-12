package tech.readresolve.skilltree.config;

import java.time.ZoneId;
import java.util.List;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.DelegatingOAuth2TokenValidator;
import org.springframework.security.oauth2.core.OAuth2TokenValidator;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtIssuerValidator;
import org.springframework.security.oauth2.jwt.JwtTimestampValidator;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

import com.auth0.jwt.algorithms.Algorithm;

@Configuration
class SecurityConfig {

    @Value("${skilltree.cors.enabled}")
    private boolean corsEnabled;

    @Value("${skilltree.security.jwt.issuer}")
    private String issuer;

    @Value("${skilltree.security.jwt.expiration}")
    private long expiration;

    @Value("${skilltree.security.jwt.zoneId}")
    private String zoneId;

    @Value("${skilltree.security.jwt.secret}")
    private String secret;

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http.cors(corsCustomizer()).csrf(csrf -> csrf.disable())
		.logout(logout -> logout.disable())
		.sessionManagement(management -> management.disable())
		// ------
		.authorizeHttpRequests(req -> req
			.requestMatchers(HttpMethod.POST, "/accounts/sign-in")
			.anonymous())
		.authorizeHttpRequests(
			req -> req
				.requestMatchers(HttpMethod.POST, "/accounts",
					"/certifications", "/activities",
					"/skills", "/trainings")
				.hasRole("ADMIN"))
		.authorizeHttpRequests(
			req -> req
				.requestMatchers(HttpMethod.PATCH,
					"/accounts/{id}/password")
				.hasRole("ADMIN"))
		.authorizeHttpRequests(
			req -> req.requestMatchers(HttpMethod.GET, "/accounts")
				.hasRole("ADMIN"))
		.authorizeHttpRequests(
			reqs -> reqs.anyRequest().authenticated())
		.oauth2ResourceServer(
			srv -> srv.jwt(Customizer.withDefaults()));
	return http.build();
    }

    private Customizer<CorsConfigurer<HttpSecurity>> corsCustomizer() {
	return corsEnabled ? Customizer.withDefaults() : cors -> cors.disable();
    }

    private OAuth2TokenValidator<Jwt> tokenValidator() {
	List<OAuth2TokenValidator<Jwt>> validators = List.of(
		new JwtTimestampValidator(), new JwtIssuerValidator(issuer));
	return new DelegatingOAuth2TokenValidator<>(validators);
    }

    @Bean
    JwtDecoder jwtDecoder() {
	SecretKey secretKey = new SecretKeySpec(secret.getBytes(),
		"HMACSHA256");
	NimbusJwtDecoder decoder = NimbusJwtDecoder.withSecretKey(secretKey)
		.macAlgorithm(MacAlgorithm.HS256).build();
	decoder.setJwtValidator(tokenValidator());
	return decoder;
    }

    @Bean
    JwtProvider jwtProvider() {
	ZoneId id = ZoneId.of(zoneId);
	Algorithm algorithm = Algorithm.HMAC256(secret);
	return new JwtProvider(issuer, expiration, id, algorithm);
    }

    @Bean
    SecurityHelper securityHelper() {
	return new SecurityHelper(jwtProvider(), new BCryptPasswordEncoder());
    }

}
