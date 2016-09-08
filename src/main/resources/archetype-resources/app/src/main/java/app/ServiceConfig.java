package ${package}.app;

import org.biins.core.service.BasicService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public BasicService basicService() {
        return new BasicService();
    }
}