package tech.readresolve.skilltree.features;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.core.type.TypeReference;

import tech.readresolve.skilltree.BaseIntegrationTests;
import tech.readresolve.skilltree.dtos.out.CertificationLevelLabelValue;

@DisplayName("Certification levels features integration tests")
class CertificationLevelsIT extends BaseIntegrationTests {

    @DisplayName("Should return all certification levels")
    @ParameterizedTest
    @ValueSource(strings = { "admin", "trainer" })
    void shouldReturnAllCertificationLevels(String tokenName) throws Exception {
	MvcResult result = perform("GET", "/certification-levels/label-values",
		tokenName).andExpect(status().is(200)).andReturn();
	String body = result.getResponse().getContentAsString();
	List<CertificationLevelLabelValue> labelValues = objectMapper.readValue(
		body, new TypeReference<List<CertificationLevelLabelValue>>() {
		});
	assertEquals(1, labelValues.size());
	CertificationLevelLabelValue labelValue = labelValues.getFirst();
	assertNotNull(labelValue.id());
	assertEquals(6, labelValue.europeanLevel());
	assertEquals("Licence, licence professionnelle, BUT",
		labelValue.equivalence());
    }

}
