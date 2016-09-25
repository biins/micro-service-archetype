package ${package}.client.http;

import java.util.Optional;

import ${package}.client.api.test.Message;
import ${package}.client.http.config.BaseFeignClientConfig;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import feign.Feign;
import feign.Logger;

// TODO: remove
@FeignClient(
		name = "${http.client.test.url}", // MUST be unique per app for proper configuration
		path = "/test",
		decode404 = true,
		configuration = HealthCheckResourceClientConfig.class
)
public interface HealthCheckResourceClient {

	@RequestMapping("/")
	Optional<Message> sayHello(@RequestParam("name") String name, @RequestHeader("origin") String origin);

}

class HealthCheckResourceClientConfig extends BaseFeignClientConfig {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	@Bean
	Feign.Builder feignBuilder() {
		return new Feign.Builder();
	}
}