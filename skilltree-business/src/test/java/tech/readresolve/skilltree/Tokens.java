package tech.readresolve.skilltree;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class Tokens {

    @Value("${skilltree.tests.fake-token}")
    private String fake;

    @Value("${skilltree.tests.bad-secret-token}")
    private String badSecret;

    @Value("${skilltree.tests.bad-issuer-token}")
    private String badIssuer;

    @Value("${skilltree.tests.expired-token}")
    private String expired;

    @Value("${skilltree.tests.valid-admin-token}")
    private String admin;

    @Value("${skilltree.tests.valid-trainer-token}")
    private String trainer;

    String get(final String name) {
	switch (name) {
	    case "fake":
		return fake;
	    case "badSecret":
		return badSecret;
	    case "badIssuer":
		return badIssuer;
	    case "expired":
		return expired;
	    case "admin":
		return admin;
	    case "trainer":
		return trainer;
	    default:
		throw new IllegalArgumentException("Unexpected value: " + name);
	}
    }

}
