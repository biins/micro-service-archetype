package ${package}.app.config.http;

import ${package}.client.http.HealthCheckResourceClient;
import ${package}.client.http.HealthCheckResourceHystrixClient;
import org.biins.commons.feign.JaxRsErrorDecoder;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.codec.ErrorDecoder;

@Configuration
@EnableFeignClients(
		clients = {
				HealthCheckResourceClient.class,
				HealthCheckResourceHystrixClient.class
		}
)
@EnableHystrix
public class HttpClientsConfig {

	@Bean
	public ErrorDecoder feignErrorDecoder() {
		return new JaxRsErrorDecoder();
	}
}
