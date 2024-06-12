package tech.readresolve.skilltree.services.helpers;

import jakarta.mail.internet.MimeMessage;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class Mailer {

    private final static Log LOGGER = LogFactory.getLog(Mailer.class);

    @Value("${skilltree.mail.reply-to}")
    private String replyTo;

    @Value("${skilltree.mail.from}")
    private String from;

    private final JavaMailSender sender;

    private final MailTemplates templates;

    Mailer(JavaMailSender sender, MailTemplates templates) {
	this.sender = sender;
	this.templates = templates;
    }

    @Async
    public void sendAccountCreated(String to, String firstname,
	    String password) {
	Mail mail = templates.accountCreated(to, firstname, password);
	send(mail);
    }

    @Async
    public void sendResetPassword(String to, String firstname,
	    String password) {
	Mail mail = templates.resetPassword(to, firstname, password);
	send(mail);
    }

    private void send(Mail mail) {
	try {
	    MimeMessage message = sender.createMimeMessage();
	    MimeMessageHelper helper = new MimeMessageHelper(message);
	    helper.setReplyTo(replyTo);
	    helper.setFrom(from);
	    helper.setTo(mail.to());
	    helper.setSubject(mail.subject());
	    helper.setText(mail.content(), true);
	    sender.send(message);
	} catch (Exception ex) {
	    LOGGER.error(String.format("Error sending async mail to '%s'",
		    mail.to()), ex);
	}
    }

}
