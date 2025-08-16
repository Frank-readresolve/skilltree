package tech.readresolve.skilltree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebAutoConfiguration;
import org.springframework.boot.autoconfigure.http.client.HttpClientAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcClientAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.sql.init.SqlInitializationAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.boot.autoconfigure.web.client.RestTemplateAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;

import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

@SpringBootApplication(exclude = { ErrorMvcAutoConfiguration.class,
	HttpClientAutoConfiguration.class, JdbcClientAutoConfiguration.class,
	JdbcTemplateAutoConfiguration.class, MultipartAutoConfiguration.class,
	RestClientAutoConfiguration.class, RestTemplateAutoConfiguration.class,
	SpringDataWebAutoConfiguration.class,
	SqlInitializationAutoConfiguration.class,
	WebSocketServletAutoConfiguration.class })
public class SkillTree {

    @ExcludeFromJacocoGeneratedReport
    public static void main(String[] args) {
	SpringApplication.run(SkillTree.class, args);
    }

}
