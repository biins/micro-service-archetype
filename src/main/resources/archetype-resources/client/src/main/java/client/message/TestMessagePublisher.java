package ${package}.client.message;

import ${package}.client.api.test.Message;
import org.biins.commons.logging.Logger;
import org.biins.commons.logging.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

// TODO: remove
public class TestMessagePublisher {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestMessagePublisher.class);

	private final RabbitTemplate rabbitTemplate;

	public TestMessagePublisher(RabbitTemplate rabbitTemplate) {
		this.rabbitTemplate = rabbitTemplate;
	}

	@HystrixCommand(fallbackMethod = "fallback")
	public void publish(Message message) {
		rabbitTemplate.convertAndSend(message);
	}

	public void fallback(Message message) {
		LOGGER.asJson().error("Can't sent message '{}'", message);
	}
}
