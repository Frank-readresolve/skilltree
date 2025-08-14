package tech.readresolve.skilltree.constraints;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import tech.readresolve.skilltree.BaseMvcTests;
import tech.readresolve.skilltree.services.helpers.Mailer;
import tech.readresolve.skilltree.services.helpers.MailerException;

@DisplayName("Tests mailer against SMTP constraints")
class MailerTests extends BaseMvcTests {

    @Autowired
    private Mailer mailer;

    @DisplayName("Should mail be rejected")
    @Test
    void shouldNotSendMail() {
	assertThrowsExactly(MailerException.class, () -> mailer
		.sendAccountCreated("toto@localhost", "Toto", "xxx"));
	assertThrowsExactly(MailerException.class, () -> mailer
		.sendResetPassword("toto@localhost", "Toto", "xxx"));
    }

    @DisplayName("Should mail be accepted")
    @Test
    void shouldSendMail() {
	assertDoesNotThrow(() -> mailer
		.sendAccountCreated("toto@readresolve.io", "Toto", "xxx"));
	assertDoesNotThrow(() -> mailer.sendResetPassword("toto@readresolve.io",
		"Toto", "xxx"));
    }

}
