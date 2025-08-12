package tech.readresolve.skilltree.features;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import tech.readresolve.skilltree.BaseIntegrationTests;
import tech.readresolve.skilltree.Interpolator;
import tech.readresolve.skilltree.entities.Certification;

@DisplayName("Certifications features integration tests")
class CertificationsIT extends BaseIntegrationTests {

	private static final String PATH = "/csv/features/certifications/";

	private static final String CERTIFICATION_BY_CODE = """
    	select c from Certification c
    		join fetch c.level l
    			where c.code = '%s'
    	""";

	@DisplayName("Should create a new certification")
	@ParameterizedTest
	@CsvFileSource(resources = PATH
			+ "create.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldCreateCertification(String json) throws Exception {
		var interpolated = Interpolator.interpolate(json);
		perform("POST", "/certifications", "admin", interpolated)
				.andExpect(status().is(204));
		var certification = findEntity(Certification.class,
				CERTIFICATION_BY_CODE, asString(interpolated, "$.code"));
		assertThat(certification).isNotNull();
		assertThat(certification.getLevel().getId())
				.isEqualTo(asLong(interpolated, "$.certificationLevelId"));
		assertThat(certification.getCode())
				.isEqualTo(asString(interpolated, "$.code"));
		assertThat(certification.getName())
				.isEqualTo(asString(interpolated, "$.name"));
		assertThat(certification.getAcronym())
				.isEqualTo(asString(interpolated, "$.acronym"));
		assertThat(certification.getStartYear())
				.isEqualTo(asYear(interpolated, "$.startYear"));
		assertThat(certification.getDescription())
				.isEqualTo(asString(interpolated, "$.description"));
	}

	@DisplayName("Should return all certifications")
	@ParameterizedTest
	@ValueSource(strings = { "admin", "trainer" })
	void shouldReturnAllCertifications(String tokenName) throws Exception {
		perform("GET", "/certifications/label-values", tokenName)
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.length()", is(2)));
	}

	@DisplayName("Should return all activities for one certification")
	@ParameterizedTest
	@ValueSource(strings = { "admin", "trainer" })
	void shouldReturnAllActivitiesForOneCertification(String tokenName)
			throws Exception {
		perform("GET", "/certifications/1/activities/label-values", tokenName)
				.andExpect(status().is(200))
				.andExpect(jsonPath("$.length()", is(3)));
	}

}
