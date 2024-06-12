package tech.readresolve.skilltree.constraints;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import tech.readresolve.skilltree.BaseMvcTests;
import tech.readresolve.skilltree.ControllerMocks;

@DisplayName("Tests endpoints against CORS")
@Import(ControllerMocks.class)
class Cors extends BaseMvcTests {

    @Value("${skilltree.cors.allowedOrigins}")
    private String[] allowedOrigins;

    private final static String PATH = "/csv/constraints/cors/";

    private final static String END_POINTS = PATH + "endpoints.csv";

    @DisplayName("Should CORS request be accepted")
    @ParameterizedTest
    @CsvFileSource(resources = END_POINTS, numLinesToSkip = 1,
	    delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldCorsBeAccepted(String method, String path) throws Exception {
	MockHttpServletRequestBuilder builder = requestBuilder("OPTIONS", path,
		"anonymous").header("Access-Control-Request-Method", method)
		.header("Origin", allowedOrigins[0]);
	perform(builder).andExpect(status().is(200));
    }

}
