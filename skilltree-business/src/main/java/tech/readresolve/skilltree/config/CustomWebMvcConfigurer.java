package tech.readresolve.skilltree.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty(value = "skilltree.cors.enabled", havingValue = "true", matchIfMissing = true)
class CustomWebMvcConfigurer implements WebMvcConfigurer {

	@Value("${skilltree.cors.allowedOrigins}")
	private String[] allowedOrigins;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins(allowedOrigins)
				.allowedMethods("POST", "GET", "PUT", "PATCH", "DELETE");
	}

}
