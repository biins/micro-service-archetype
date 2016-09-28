package ${package}.app.config.rabbit;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import({
        TestMessageRabbitMqConfig.class
})
public class RabbitMqConfig {
}
