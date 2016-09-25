package ${package}.app.config.health;

import ${package}.core.health.AnotherServiceHealthIndicator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HealthIndicatorConfig {

    @Bean
    public AnotherServiceHealthIndicator anotherServiceHealthIndicator() {
        return new AnotherServiceHealthIndicator();
    }
}
