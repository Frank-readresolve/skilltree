package tech.readresolve.skilltree.features;

import static org.assertj.core.api.Assertions.assertThat;
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
	void shouldCreateActivity(String json) throws Exception {
		perform("POST", "/activities", "admin", json)
				.andExpect(status().is(204));
		var activity = findEntity(Activity.class, ACTIVITY_BY_CODE,
				asString(json, "$.code"));
		assertThat(activity).isNotNull();
		assertThat(activity.getCertification().getId())
				.isEqualTo(asLong(json, "$.certificationId"));
		assertThat(activity.getName()).isEqualTo(asString(json, "$.name"));
		assertThat(activity.getDescription())
				.isEqualTo(asString(json, "$.description"));
	}

}
