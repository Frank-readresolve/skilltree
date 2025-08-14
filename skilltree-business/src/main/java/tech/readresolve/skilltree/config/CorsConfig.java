package tech.readresolve.skilltree.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@ConditionalOnProperty(value = "skilltree.cors.enabled", havingValue = "true", matchIfMissing = true)
class CorsConfig implements WebMvcConfigurer {

    @Value("${skilltree.cors.allowedOrigins}")
    private String[] allowedOrigins;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
	registry.addMapping("/accounts").allowedMethods("POST", "GET")
		.allowedOrigins(allowedOrigins);
	registry.addMapping("/accounts/sign-in").allowedMethods("POST")
		.allowedOrigins(allowedOrigins);
	registry.addMapping("/accounts/{id}/password").allowedMethods("PATCH")
		.allowedOrigins(allowedOrigins);
	registry.addMapping("/activities").allowedMethods("POST")
		.allowedOrigins(allowedOrigins);
	registry.addMapping("/certifications").allowedMethods("POST")
		.allowedOrigins(allowedOrigins);
	registry.addMapping("/certifications/label-values")
		.allowedMethods("GET").allowedOrigins(allowedOrigins);
	registry.addMapping("/certifications/{id}/activities/label-values")
		.allowedMethods("GET").allowedOrigins(allowedOrigins);
	registry.addMapping("/certification-levels/label-values")
		.allowedMethods("GET").allowedOrigins(allowedOrigins);
	registry.addMapping("/skills").allowedMethods("POST")
		.allowedOrigins(allowedOrigins);
	registry.addMapping("/trainings").allowedMethods("POST")
		.allowedOrigins(allowedOrigins);
    }

}
