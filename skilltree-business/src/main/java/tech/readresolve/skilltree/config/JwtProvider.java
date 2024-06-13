package tech.readresolve.skilltree.config;

import java.time.Instant;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

public record JwtProvider(String issuer, long expiration,
	Algorithm algorithm) {

    String create(String subject, List<String> roles) {
	Instant issuedAt = Instant.now();
	Builder builder = JWT.create().withIssuer(issuer)
		.withIssuedAt(issuedAt);
	if (expiration > -1) {
	    Instant expiresAt = issuedAt
		    .plusSeconds(expiration);
	    builder.withExpiresAt(expiresAt);
	}
	builder.withSubject(subject).withClaim("roles",
		roles);
	return builder.sign(algorithm);
    }

}
