package tech.readresolve.skilltree.features;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.jayway.jsonpath.JsonPath;

import tech.readresolve.skilltree.BaseIntegrationTests;
import tech.readresolve.skilltree.entities.Training;

@DisplayName("Trainings features integration tests")
class TrainingsIT extends BaseIntegrationTests {

	private static final String PATH = "/csv/features/trainings/";

	private static final String TRAINING_BY_NAME = """
    	select t from Training t
    		join fetch t.certification c
    			where t.name = '%s'
    	""";

	@DisplayName("Should create a new training")
	@ParameterizedTest
	@CsvFileSource(resources = PATH
			+ "create.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldCreateTraining(String json) throws Exception {
		perform("POST", "/trainings", "admin", json)
				.andExpect(status().is(204));
		var name = JsonPath.read(json, "$.name");
		var training = findEntity(Training.class, TRAINING_BY_NAME, name);
		assertThat(training).isNotNull();
		assertThat(training.getCertification().getId())
				.isEqualTo(asLong(json, "$.certificationId"));
		assertThat(training.getStartDate())
				.isEqualTo(asLocalDate(json, "$.startDate"));
		assertThat(training.getEndDate())
				.isEqualTo(asLocalDate(json, "$.endDate"));
		assertThat(training.getDescription())
				.isEqualTo(asString(json, "$.description"));
	}

}
