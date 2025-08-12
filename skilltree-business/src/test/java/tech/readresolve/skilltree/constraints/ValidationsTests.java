package tech.readresolve.skilltree.constraints;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.springframework.transaction.annotation.Transactional;

import tech.readresolve.skilltree.BaseMvcTests;
import tech.readresolve.skilltree.ControllerMocks;
import tech.readresolve.skilltree.Interpolator;

@DisplayName("Tests inputs against validation constraints")
@Transactional
@ControllerMocks
class ValidationsTests extends BaseMvcTests {

	private static final String PATH = "/csv/constraints/validations/";

	@DisplayName("Should inputs be not valid (422) with specific error code")
	@ParameterizedTest
	@CsvFileSource(resources = { PATH + "account-create-not-valid.csv",
			PATH + "activity-create-not-valid.csv",
			PATH + "certification-create-not-valid.csv",
			PATH + "sign-in-not-valid.csv", PATH + "skill-create-not-valid.csv",
			PATH + "training-create-not-valid.csv" }, numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldBeNotValid(String method, String path, String tokenName,
			String json, String field, String error) throws Exception {
		var jsonPath = (String) null;
		if (null != field) { // Field error
			jsonPath = String.format("$.errors.*.%s[*].code", field);
		} else { // Global error
			jsonPath = "$.errors.globals.*.code";
		}
		var interpolated = Interpolator.interpolate(json);
		perform(method, path, tokenName, interpolated)
				.andExpect(status().is(422))
				.andExpect(jsonPath(jsonPath, hasItem(error)));
	}

	@DisplayName("Should inputs be valid (2xx successful)")
	@ParameterizedTest
	@CsvFileSource(resources = { PATH + "account-create-valid.csv",
			PATH + "activity-create-valid.csv",
			PATH + "certification-create-valid.csv", PATH + "sign-in-valid.csv",
			PATH + "skill-create-valid.csv",
			PATH + "training-create-valid.csv" }, numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldBeValid(String method, String path, String tokenName,
			String json) throws Exception {
		var interpolated = Interpolator.interpolate(json);
		perform(method, path, tokenName, interpolated)
				.andExpect(status().is2xxSuccessful());
	}

}
