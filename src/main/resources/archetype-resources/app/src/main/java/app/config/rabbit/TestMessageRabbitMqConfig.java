package ${package}.app.config.rabbit;

import ${package}.core.message.TestMessageHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;

@ConditionalOnProperty(name = "rabbitmq.message.test.enable", havingValue = "true")
@ConfigurationProperties(prefix = "rabbitmq.message.test")
public class TestMessageRabbitMqConfig extends BaseRabbitMqConfig {

    @Override
    @Bean
    TestMessageHandler receiver() {
        return new TestMessageHandler();
    }

}
