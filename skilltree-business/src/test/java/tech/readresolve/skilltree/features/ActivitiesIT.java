package tech.readresolve.skilltree.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import tech.readresolve.skilltree.BaseIntegrationTests;
import tech.readresolve.skilltree.entities.Activity;

@DisplayName("Activities features integration tests")
class ActivitiesIT extends BaseIntegrationTests {

    private static final String PATH = "/csv/features/activities/";

    private static final String ACTIVITY_BY_CODE = """
    	select a from Activity a
    		join fetch a.certification c
    			where a.code = '%s'
    	""";

    @DisplayName("Should create a new activity")
    @ParameterizedTest
    @CsvFileSource(resources = PATH
	    + "create.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldCreate(String json) throws Exception {
	perform("POST", "/activities", "admin", json)
		.andExpect(status().is(204));
	var activity = findEntity(Activity.class, ACTIVITY_BY_CODE,
		asString(json, "$.code"));
	assertNotNull(activity);
	assertEquals(asLong(json, "$.certificationId"),
		activity.getCertification().getId());
	assertEquals(asString(json, "$.code"), activity.getCode());
	assertEquals(asString(json, "$.name"), activity.getName());
	assertEquals(asString(json, "$.description"),
		activity.getDescription());
    }

}
