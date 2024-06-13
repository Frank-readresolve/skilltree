package tech.readresolve.skilltree.config;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator.Builder;
import com.auth0.jwt.algorithms.Algorithm;

public record JwtProvider(String issuer, long expiration,
	ZoneId zoneId, Algorithm algorithm) {

    String create(String subject, List<String> roles) {
	LocalDateTime now = LocalDateTime.now(zoneId);
	Instant issuedAt = now.atZone(zoneId).toInstant();
	Builder builder = JWT.create().withIssuer(issuer)
		.withIssuedAt(issuedAt);
	if (expiration > -1) {
	    LocalDateTime expires = now
		    .plusSeconds(expiration);
	    Instant expiresAt = expires.atZone(zoneId)
		    .toInstant();
	    builder.withExpiresAt(expiresAt);
	}
	builder.withSubject(subject).withClaim("roles",
		roles);
	return builder.sign(algorithm);
    }

}
