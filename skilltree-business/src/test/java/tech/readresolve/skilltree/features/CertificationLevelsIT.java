package tech.readresolve.skilltree.features;

import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import tech.readresolve.skilltree.BaseIntegrationTests;

@DisplayName("Certification levels features integration tests")
class CertificationLevelsIT extends BaseIntegrationTests {

	@DisplayName("Should return all certification levels")
	@ParameterizedTest
	@ValueSource(strings = { "admin", "trainer" })
	void shouldReturnAllCertificationLevels(String tokenName) throws Exception {
		perform("GET", "/certification-levels/label-values", tokenName)
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.length()", is(1)));
	}

}
