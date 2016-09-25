package ${package}.client.message.config;

import ${package}.client.message.RabbitTemplateFactory;
import ${package}.client.message.TestMessagePublisher;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: remove
@Configuration
@ConfigurationProperties(prefix = "rabbitmq.message.test")
public class TestMessageProducerConfig extends RabbitTemplateFactory {

	@Bean
	RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
		return createRabbitTemplate(connectionFactory);
	}

	@Bean
	public TestMessagePublisher testMessagePublisher(RabbitTemplate rabbitTemplate) {
		return new TestMessagePublisher(rabbitTemplate);
	}
}
