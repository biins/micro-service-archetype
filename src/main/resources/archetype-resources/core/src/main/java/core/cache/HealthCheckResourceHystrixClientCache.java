package ${package}.core.cache;

import java.util.Optional;

import javax.inject.Inject;

import ${package}.client.api.test.Message;
import ${package}.client.http.HealthCheckResourceHystrixClient;
import org.biins.commons.hystrix.HystrixCacheLoader;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.interceptor.SimpleKey;
import org.springframework.stereotype.Component;

@Component
public class HealthCheckResourceHystrixClientCache {

    private final HealthCheckResourceHystrixClient client;
    private final Cache cache;

    @Inject
    public HealthCheckResourceHystrixClientCache(HealthCheckResourceHystrixClient client, CacheManager cacheManager) {
        this.client = client;
        this.cache = cacheManager.getCache("hello");
    }

    public Optional<Message> sayHello(String name, String origin) {
        return HystrixCacheLoader.of(new SimpleKey(name, origin), Message.class)
                .with(cache)
                .getAndSet(() -> client.sayHelloToHystrixCommand(name, origin));
    }
}
