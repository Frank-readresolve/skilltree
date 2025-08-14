package tech.readresolve.skilltree.services.helpers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
class MailTemplates {

    @Value("${skilltree.mail-template.account-created.subject}")
    private String createdSubject;

    @Value("${skilltree.mail-template.account-created.body}")
    private String createdBody;

    @Value("${skilltree.mail-template.reset-password.subject}")
    private String resetSubject;

    @Value("${skilltree.mail-template.reset-password.body}")
    private String resetBody;

    Mail accountCreated(String to, String firstname, String password) {
	String content = String.format(createdBody, firstname, to, password);
	return new Mail(to, createdSubject, content);
    }

    Mail resetPassword(String to, String firstname, String password) {
	String content = String.format(resetBody, firstname, password);
	return new Mail(to, resetSubject, content);
    }

}
