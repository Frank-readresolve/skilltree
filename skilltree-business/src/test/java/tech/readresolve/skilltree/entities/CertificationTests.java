package tech.readresolve.skilltree.entities;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static tech.readresolve.skilltree.Verifiers.verifyToStringOverriden;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import tech.readresolve.skilltree.BaseMvcTests;

@DisplayName("Tests Certification entity against consistency and invariants")
class CertificationTests extends BaseMvcTests {

    @DisplayName("Should toString() be overriden")
    @Test
    void shouldToStringBeOverriden() throws Exception {
	assertTrue(verifyToStringOverriden(new Certification()));
    }

}
