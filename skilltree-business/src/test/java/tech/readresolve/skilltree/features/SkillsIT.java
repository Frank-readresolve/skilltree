package tech.readresolve.skilltree.features;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import tech.readresolve.skilltree.BaseIntegrationTests;
import tech.readresolve.skilltree.entities.Skill;

@DisplayName("Skills features integration tests")
class SkillsIT extends BaseIntegrationTests {

	private static final String PATH = "/csv/features/skills/";

	private static final String SKILL_BY_CODE = """
    	select s from Skill s
    		join fetch s.activity a
    			where s.code = '%s'
    	""";

	@DisplayName("Should create a new skill")
	@ParameterizedTest
	@CsvFileSource(resources = PATH
			+ "create.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
	void shouldCreateSkill(String json) throws Exception {
		perform("POST", "/skills", "admin", json).andExpect(status().is(204));
		var skill = findEntity(Skill.class, SKILL_BY_CODE,
				asString(json, "$.code"));
		assertThat(skill).isNotNull();
		assertThat(skill.getActivity().getId())
				.isEqualTo(asLong(json, "$.activityId"));
		assertThat(skill.getName()).isEqualTo(asString(json, "$.name"));
		assertThat(skill.getDescription())
				.isEqualTo(asString(json, "$.description"));
	}

}
