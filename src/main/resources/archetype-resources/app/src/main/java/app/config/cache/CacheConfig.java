package ${package}.app.config.cache;

import java.util.List;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.support.SimpleCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
		HelloCache.class
})
@EnableCaching
public class CacheConfig {

	@Bean
	public CacheManager cacheManager(List<Cache> caches) {
		SimpleCacheManager simpleCacheManager = new SimpleCacheManager();
		simpleCacheManager.setCaches(caches);
		return simpleCacheManager;
	}
}
