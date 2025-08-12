package tech.readresolve.skilltree.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ConditionalOnProperty(value = "skilltree.async.enabled", havingValue = "true", matchIfMissing = true)
class AsynConfig {
	// Empty class
}
