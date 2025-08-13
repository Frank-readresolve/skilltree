package tech.readresolve.skilltree.constraints;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import tech.readresolve.skilltree.BaseMvcTests;
import tech.readresolve.skilltree.services.helpers.Mailer;

@DisplayName("Tests inputs against database constraints")
@Transactional
class ResourceNotFoundTests extends BaseMvcTests {

	private static final String PATH = "/csv/constraints/resource-not-found/";

	@MockitoBean
	private Mailer mailer;

	@MockitoBean
	private LocalValidatorFactoryBean validator;

	@DisplayName("Should resource be not found (404)")
	@ParameterizedTest
	@CsvFileSource(resources = PATH
			+ "endpoints.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldResourceBeNotFound(String method, String path, String tokenName)
			throws Exception {
		MockHttpServletRequestBuilder builder = requestBuilder(method, path,
				tokenName);
		perform(builder).andExpect(status().is(404));
	}

}
