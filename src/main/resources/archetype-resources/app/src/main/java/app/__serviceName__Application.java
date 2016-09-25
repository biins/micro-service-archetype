package ${package}.app;

import ${package}.app.config.cache.CacheConfig;
import ${package}.app.config.http.HttpClientsConfig;
import ${package}.app.config.health.HealthIndicatorConfig;
import ${package}.app.config.jersey.JerseyConfig;
import ${package}.app.config.db.MongoConfig;
import ${package}.app.config.job.SchedulerConfig;
import ${package}.app.config.properties.PropertiesConfig;
import ${package}.app.config.db.RepositoryConfig;
import ${package}.app.config.CoreConfig;
import ${package}.app.config.rabbit.RabbitMqConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.PropertySource;

@SpringBootConfiguration
@EnableAutoConfiguration
@Import({
        PropertiesConfig.class,
        JerseyConfig.class,
        CoreConfig.class,
        MongoConfig.class,
        RepositoryConfig.class,
        HealthIndicatorConfig.class,
        RabbitMqConfig.class,
        SchedulerConfig.class,
        HttpClientsConfig.class,
        CacheConfig.class
})
@PropertySource(value = {
        "classpath:environment.properties", "/etc/environment.properties",
        "classpath:application.properties", "classpath:application.yaml"
}, ignoreResourceNotFound = true)
public class ${serviceName}Application {

    public static void main(String[] args) {
        SpringApplication.run(${serviceName}Application.class, args);
    }
}