package tech.readresolve.skilltree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
@EnableCaching
public class Skilltree {

    public static void main(String[] args) {
	SpringApplication.run(Skilltree.class, args);
    }

    @Bean
    CacheManager cacheManager() {
	return new ConcurrentMapCacheManager("default-cache");
    }

}
