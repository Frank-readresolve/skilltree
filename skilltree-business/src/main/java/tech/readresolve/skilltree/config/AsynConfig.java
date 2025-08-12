package tech.readresolve.skilltree.config;

import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ConditionalOnProperty(value = "skilltree.async.enabled", havingValue = "true", matchIfMissing = true)
class AsynConfig implements AsyncConfigurer {

	private static final Log LOGGER = LogFactory.getLog(AsynConfig.class);

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return new AsyncUncaughtExceptionHandler() {

			@Override
			public void handleUncaughtException(Throwable ex, Method method,
					Object... params) {
				System.out.println("############ handleUncaughtException");
				LOGGER.error(ex);
			}

		};
	}

}
