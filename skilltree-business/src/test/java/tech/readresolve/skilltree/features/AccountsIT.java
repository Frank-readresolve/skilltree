package tech.readresolve.skilltree.features;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.auth0.jwt.JWT;
import com.jayway.jsonpath.JsonPath;

import tech.readresolve.skilltree.BaseIntegrationTests;
import tech.readresolve.skilltree.entities.Account;
import tech.readresolve.skilltree.entities.Role;
import tech.readresolve.skilltree.entities.Trainer;

@DisplayName("Accounts features integration tests")
class AccountsIT extends BaseIntegrationTests {

    private static final String PATH = "/csv/features/accounts/";

    private static final String TRAINER_CODE_PATTERN = "^FO[0-9]{8}$";

    private static final String TRAINER_BY_USERNAME = """
    	select t from Trainer t
    		join fetch t.account a join fetch a.role r
    			where a.username = '%s'
    	""";

    private static final String ACCOUNT_BY_USERNAME = """
    	select a from Account a
    		join fetch a.role r
    			where a.username = '%s'
    	""";

    @DisplayName("Should sign-in and return valid token and auth info")
    @ParameterizedTest
    @CsvFileSource(resources = PATH
	    + "sign-in.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldSignIn(String json) throws Exception {
	var result = perform("POST", "/accounts/sign-in", "anonymous", json)
		.andExpect(status().is(200)).andReturn();
	var account = findEntity(Account.class, ACCOUNT_BY_USERNAME,
		asString(json, "$.username"));
	var body = result.getResponse().getContentAsString();
	// verify token:
	var decoded = JWT.decode(asString(body, "$.token"));
	var sub = decoded.getSubject();
	assertEquals(sub, account.getUsername());
	var roles = decoded.getClaim("roles").asList(String.class);
	assertTrue(roles.contains(account.getRole().getCode()));
	// verify auth info:
	assertEquals(asString(body, "$.accountInfo.firstname"),
		account.getFirstname());
	assertEquals(asString(body, "$.accountInfo.lastname"),
		account.getLastname());
	assertEquals(asString(body, "$.accountInfo.role"),
		account.getRole().getCode());
    }

    @DisplayName("Should not sign-in and return 401")
    @ParameterizedTest
    @CsvFileSource(resources = PATH
	    + "sign-in-rejected.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldNotSignIn(String json) throws Exception {
	perform("POST", "/accounts/sign-in", "anonymous", json)
		.andExpect(status().isUnauthorized());
    }

    @DisplayName("Should create a new account with default role")
    @ParameterizedTest
    @CsvFileSource(resources = PATH
	    + "create.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldCreate(String json) throws Exception {
	perform("POST", "/accounts", "admin", json).andExpect(status().is(204));
	var username = JsonPath.read(json, "$.username");
	var trainer = findEntity(Trainer.class, TRAINER_BY_USERNAME, username);
	assertNotNull(trainer);
	assertNotNull(trainer.getId());
	assertNotNull(trainer.getCode());
	// FIXME: assertThat deprecated
	assertTrue(trainer.getCode().matches(TRAINER_CODE_PATTERN));
	var account = trainer.getAccount();
	assertNotNull(account);
	assertNotNull(account.getId());
	assertEquals(asString(json, "$.firstname"), account.getFirstname());
	assertEquals(asString(json, "$.lastname"), account.getLastname());
	assertNotNull(account.getPassword());
	var role = account.getRole();
	assertNotNull(role);
	assertEquals(Role.DEFAULT, role.getCode());
    }

    @DisplayName("Should reset an account password")
    @Test
    void shouldResetPassword() throws Exception {
	var account = findEntity(Account.class, ACCOUNT_BY_USERNAME,
		"skilltree-reset-test@readresolve.io");
	var id = account.getId();
	var currentHash = account.getPassword();
	perform("PATCH", String.format("/accounts/%s/password", id), "admin")
		.andExpect(status().is(204));
	account = findEntity(Account.class, id);
	var newHash = account.getPassword();
	assertNotEquals(newHash, currentHash);
    }

    @DisplayName("Should get all accounts (excluding authenticated admin user)")
    @Test
    void shouldGetAllAccounts() throws Exception {
	perform("GET", "/accounts", "admin").andExpect(status().is(200))
		.andExpect(jsonPath("$.length()", is(2))).andExpect(jsonPath(
			"$[*].username", not(hasItem("admin@localhost"))));
    }

}
