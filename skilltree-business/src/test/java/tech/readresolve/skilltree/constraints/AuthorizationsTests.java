package tech.readresolve.skilltree.constraints;

import static org.hamcrest.CoreMatchers.not;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import tech.readresolve.skilltree.BaseMvcTests;
import tech.readresolve.skilltree.ControllerMocks;

@DisplayName("Tests endpoints against authorization constraints")
@ControllerMocks
class AuthorizationsTests extends BaseMvcTests {

    private static final String PATH = "/csv/constraints/authorizations/";

    private static final String END_POINTS = PATH + "endpoints.csv";

    @DisplayName("Should access be denied with expected status")
    @ParameterizedTest
    @CsvFileSource(resources = PATH
	    + "denied.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldBeDenied(String method, String path, String tokenName,
	    int expectedStatus) throws Exception {
	perform(method, path, tokenName).andExpect(status().is(expectedStatus));
    }

    @DisplayName("Should access be authorized (not 401, not 403, not 404)")
    @ParameterizedTest
    @CsvFileSource(resources = PATH
	    + "authorized.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldBeAuthorized(String method, String path, String tokenName)
	    throws Exception {
	perform(method, path, tokenName).andExpectAll(status().is(not(401)),
		status().is(not(403)), status().is(not(404)));
    }

    @DisplayName("Should access be rejected (401) with bad issuer token")
    @ParameterizedTest
    @CsvFileSource(resources = END_POINTS, numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldBadIssuerTokenBeRejected(String method, String path)
	    throws Exception {
	perform(method, path, "badIssuer").andExpect(status().is(401));
    }

    @DisplayName("Should access be rejected (401) with bad secret token")
    @ParameterizedTest
    @CsvFileSource(resources = END_POINTS, numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldBadSecretTokenBeRejected(String method, String path)
	    throws Exception {
	perform(method, path, "badSecret").andExpect(status().is(401));
    }

    @DisplayName("Should access be rejected (401) with expired token")
    @ParameterizedTest
    @CsvFileSource(resources = END_POINTS, numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldExpiredTokenBeRejected(String method, String path)
	    throws Exception {
	perform(method, path, "expired").andExpect(status().is(401));
    }

    @DisplayName("Should access be rejected (401) with fake token")
    @ParameterizedTest
    @CsvFileSource(resources = END_POINTS, numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldFakeTokenBeRejected(String method, String path)
	    throws Exception {
	perform(method, path, "fake").andExpect(status().is(401));
    }

}
