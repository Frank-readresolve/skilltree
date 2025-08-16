package tech.readresolve.skilltree.features;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void shouldCreate(String json) throws Exception {
	var interpolated = Interpolator.interpolate(json);
	perform("POST", "/certifications", "admin", interpolated)
		.andExpect(status().is(204));
	var certification = findEntity(Certification.class,
		CERTIFICATION_BY_CODE, asString(interpolated, "$.code"));
	assertNotNull(certification);
	assertNotNull(certification.getId());
	assertEquals(asLong(interpolated, "$.certificationLevelId"),
		certification.getLevel().getId());
	assertEquals(asString(interpolated, "$.code"), certification.getCode());
	assertEquals(asString(interpolated, "$.name"), certification.getName());
	assertEquals(asString(interpolated, "$.acronym"),
		certification.getAcronym());
	assertEquals(asYear(interpolated, "$.startYear"),
		certification.getStartYear());
	assertEquals(asString(interpolated, "$.description"),
		certification.getDescription());
    }

    @DisplayName("Should get all certifications")
    @ParameterizedTest
    @ValueSource(strings = { "admin", "trainer" })
    void shouldGetAll(String tokenName) throws Exception {
	perform("GET", "/certifications/label-values", tokenName)
		.andExpect(status().is(200))
		.andExpect(jsonPath("$.length()", is(2)));
    }

    @DisplayName("Should get all activities for one certification")
    @ParameterizedTest
    @ValueSource(strings = { "admin", "trainer" })
    void shouldGetAllActivitiesForOne(String tokenName) throws Exception {
	var certification = findEntity(Certification.class,
		CERTIFICATION_BY_CODE, "RNCP37873");
	var id = certification.getId();
	perform("GET",
		String.format("/certifications/%s/activities/label-values", id),
		tokenName).andExpect(status().is(200))
		.andExpect(jsonPath("$.length()", is(3)));
    }

}
