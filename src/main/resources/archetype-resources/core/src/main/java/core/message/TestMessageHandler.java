package ${package}.core.message;

import org.biins.commons.logging.Logger;
import org.biins.commons.logging.LoggerFactory;

public class TestMessageHandler implements MessageHandler<Message<String>> {

	private static final Logger LOGGER = LoggerFactory.getLogger(TestMessageHandler.class);

	@Override
	public void handleMessage(Message<String> message) {
		LOGGER.asJson().info("Receiving message '{}'", message);
	}
}
