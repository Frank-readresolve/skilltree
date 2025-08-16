package tech.readresolve.skilltree.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void shouldCreate(String json) throws Exception {
	perform("POST", "/skills", "admin", json).andExpect(status().is(204));
	var skill = findEntity(Skill.class, SKILL_BY_CODE,
		asString(json, "$.code"));
	assertNotNull(skill);
	assertNotNull(skill.getId());
	assertEquals(asLong(json, "$.activityId"), skill.getActivity().getId());
	assertEquals(asString(json, "$.code"), skill.getCode());
	assertEquals(asString(json, "$.name"), skill.getName());
	assertEquals(asString(json, "$.description"), skill.getDescription());
    }

}
