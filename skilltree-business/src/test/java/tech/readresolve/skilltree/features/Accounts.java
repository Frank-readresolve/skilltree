package tech.readresolve.skilltree.features;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.regex.Pattern;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import com.auth0.jwt.JWT;
import com.jayway.jsonpath.JsonPath;

import tech.readresolve.skilltree.BaseIntegrationTests;
import tech.readresolve.skilltree.entities.Account;
import tech.readresolve.skilltree.entities.Trainer;

@DisplayName("Accounts features tests")
class Accounts extends BaseIntegrationTests {

    private static final String PATH = "/csv/features/accounts/";

    private static final Pattern TRAINER_CODE_PATTERN = Pattern
	    .compile("^FO[0-9]{8}$");

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
	assertThat(sub).isEqualTo(account.getUsername());
	var roles = decoded.getClaim("roles").asList(String.class);
	assertThat(roles).contains(account.getRole().getCode());
	// verify auth info:
	assertThat(asString(body, "$.accountInfo.firstname"))
		.isEqualTo(account.getFirstname());
	assertThat(asString(body, "$.accountInfo.lastname"))
		.isEqualTo(account.getLastname());
	assertThat(asString(body, "$.accountInfo.role"))
		.isEqualTo(account.getRole().getCode());
    }

    @DisplayName("Should create a new (default trainer) account")
    @ParameterizedTest
    @CsvFileSource(resources = PATH
	    + "create.csv", numLinesToSkip = 1, delimiter = DELIMITER, maxCharsPerColumn = MAX_CHARS_PER_COLUMN)
    void shouldCreateAccount(String json) throws Exception {
	perform("POST", "/accounts", "admin", json).andExpect(status().is(204));
	var username = JsonPath.read(json, "$.username");
	var trainer = findEntity(Trainer.class, TRAINER_BY_USERNAME, username);
	assertThat(trainer).isNotNull();
	assertThat(trainer.getCode()).matches(TRAINER_CODE_PATTERN);
	var account = trainer.getAccount();
	assertThat(account).isNotNull();
	assertThat(account.getFirstname())
		.isEqualTo(asString(json, "$.firstname"));
	assertThat(account.getLastname())
		.isEqualTo(asString(json, "$.lastname"));
	assertThat(account.getPassword()).isNotNull();
	var role = account.getRole();
	assertThat(role).isNotNull();
	assertThat(role.getCode()).isEqualTo("ROLE_TRAINER");
    }

    @DisplayName("Should reset an account password")
    @Test
    void shouldResetPassword() throws Exception {
	var account = findEntity(Account.class, 3L);
	var currentHash = account.getPassword();
	perform("PATCH", "/accounts/3/password", "admin")
		.andExpect(status().is(204));
	account = findEntity(Account.class, 3L);
	var newHash = account.getPassword();
	assertThat(newHash).isNotEqualTo(currentHash);
    }

    @DisplayName("Should return all accounts but authenticated user")
    @Test
    void shouldReturnAllAccounts() throws Exception {
	perform("GET", "/accounts", "admin").andExpect(status().is(200))
		.andExpect(jsonPath("$.length()", is(2)));
    }

}
