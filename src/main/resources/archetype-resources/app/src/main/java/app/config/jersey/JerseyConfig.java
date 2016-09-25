package ${package}.app.config.jersey;

import ${package}.core.resource.HealthCheckResource;
import ${package}.core.resource.JsonResource;
import org.biins.commons.jersey.JacksonMapperProvider;
import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JerseyConfig extends ResourceConfig {

    public JerseyConfig() {
        // providers
        register(JacksonMapperProvider.class);

        // resources
        register(HealthCheckResource.class);
        register(JsonResource.class);
    }

}

