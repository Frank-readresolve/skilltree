package tech.readresolve.skilltree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;

import tech.readresolve.skilltree.misc.ExcludeFromJacocoGeneratedReport;

@SpringBootApplication
@EnableCaching
public class SkillTree {

    @ExcludeFromJacocoGeneratedReport
    public static void main(String[] args) {
	SpringApplication.run(SkillTree.class, args);
    }

    @Bean
    CacheManager cacheManager() {
	return new ConcurrentMapCacheManager("default-cache");
    }

}
