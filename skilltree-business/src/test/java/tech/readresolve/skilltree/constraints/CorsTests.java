package tech.readresolve.skilltree.constraints;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import tech.readresolve.skilltree.BaseMvcTests;
import tech.readresolve.skilltree.ControllerMocks;

@DisplayName("Tests endpoints against CORS configuration")
@ControllerMocks
class CorsTests extends BaseMvcTests {

	@Value("${skilltree.cors.allowedOrigins}")
	private String[] allowedOrigins;

	private static final String PATH = "/csv/constraints/cors/";

	@DisplayName("Should CORS request be accepted")
	@ParameterizedTest
	@CsvFileSource(resources = PATH
			+ "endpoints.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldCorsBeAccepted(String method, String path) throws Exception {
		MockHttpServletRequestBuilder builder = requestBuilder("OPTIONS", path,
				"anonymous").header("Access-Control-Request-Method", method)
				.header("Origin", allowedOrigins[0]);
		perform(builder).andExpect(status().is(200));
	}

	@DisplayName("Should CORS request be rejected with bad origin (403)")
	@ParameterizedTest
	@CsvFileSource(resources = PATH
			+ "endpoints.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldCorsBeRejectedWithBadOrigin(String method, String path)
			throws Exception {
		MockHttpServletRequestBuilder builder = requestBuilder("OPTIONS", path,
				"anonymous").header("Access-Control-Request-Method", method)
				.header("Origin", "http://toto.com");
		perform(builder).andExpect(status().is(403));
	}

	@DisplayName("Should CORS request be rejected with bad method (403)")
	@ParameterizedTest
	@CsvFileSource(resources = PATH
			+ "request-method-rejected.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldCorsBeRejectedWithBadMethod(String method, String path)
			throws Exception {
		MockHttpServletRequestBuilder builder = requestBuilder("OPTIONS", path,
				"anonymous").header("Access-Control-Request-Method", method)
				.header("Origin", allowedOrigins[0]);
		perform(builder).andExpect(status().is(403));
	}

}
