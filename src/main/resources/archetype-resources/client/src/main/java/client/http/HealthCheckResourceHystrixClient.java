package ${package}.client.http;

import java.util.Optional;

import ${package}.client.api.test.Message;
import ${package}.client.http.config.BaseFeignClientConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.feign.support.FallbackCommand;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.netflix.hystrix.HystrixCommand;

import feign.Logger;
import rx.Single;

// TODO: remove
@FeignClient(
		name = "${http.client.test.url}",
		path = "/test",
		decode404 = true,
		configuration = HealthCheckResourceHystrixClientConfig.class,
		fallback = HealthCheckResourceHystrixClientFallback.class
)
public interface HealthCheckResourceHystrixClient {

	@RequestMapping("/")
	Optional<Message> sayHello(@RequestParam("name") String name, @RequestHeader("origin") String origin);

	@RequestMapping("/")
	Single<Message> sayHelloToRx(@RequestParam("name") String name, @RequestHeader("origin") String origin);

	@RequestMapping("/")
	HystrixCommand<Message> sayHelloToHystrixCommand(@RequestParam("name") String name, @RequestHeader("origin") String origin);

}

class HealthCheckResourceHystrixClientFallback implements HealthCheckResourceHystrixClient {

	@Override
	public Optional<Message> sayHello(String name, String origin) {
		return Optional.empty();
	}

	@Override
	public Single<Message> sayHelloToRx(String name, String origin) {
		return Single.just(null);
	}

	@Override
	public HystrixCommand<Message> sayHelloToHystrixCommand(String name, String origin) {
		return new FallbackCommand<>(null);
	}
}

class HealthCheckResourceHystrixClientConfig extends BaseFeignClientConfig {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	HealthCheckResourceHystrixClientFallback healthCheckResourceClientFallback() {
		return new HealthCheckResourceHystrixClientFallback();
	}
}