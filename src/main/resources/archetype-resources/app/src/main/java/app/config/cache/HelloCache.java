package ${package}.app.config.cache;

import java.util.concurrent.TimeUnit;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.Cache;
import org.springframework.cache.guava.GuavaCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.cache.CacheBuilder;

@Configuration
@ConfigurationProperties(prefix = "cache.hello")
public class HelloCache extends BaseCacheConfig {

	@Bean
	public Cache helloCache() {
		return new GuavaCache("hello", CacheBuilder.newBuilder()
				.expireAfterAccess(getExpire().getSeconds(), TimeUnit.SECONDS)
				.maximumSize(getSize())
				.build());
	}
}
