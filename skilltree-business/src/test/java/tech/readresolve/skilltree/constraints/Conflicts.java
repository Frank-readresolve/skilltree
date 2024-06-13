package tech.readresolve.skilltree.constraints;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import tech.readresolve.skilltree.BaseMvcTests;
import tech.readresolve.skilltree.services.helpers.Mailer;

@DisplayName("Tests inputs against database constraints")
//@TestData
@Transactional
class Conflicts extends BaseMvcTests {

    private final static String PATH = "/csv/constraints/conflicts/";

    @MockBean
    private Mailer mailer;

    @MockBean
    private LocalValidatorFactoryBean validator;

    @DisplayName("Should inputs be conflicting and return 409")
    @ParameterizedTest
    @CsvFileSource(resources = {
	    PATH + "account-create.csv",
	    PATH + "activity-create.csv",
	    PATH + "certification-create.csv",
	    PATH + "skill-create.csv",
	    PATH + "training-create.csv" }, numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldBeConflicting(String method, String path,
	    String tokenName, String json)
	    throws Exception {
	perform(method, path, tokenName, json)
		.andExpect(status().is(409));
    }

}
