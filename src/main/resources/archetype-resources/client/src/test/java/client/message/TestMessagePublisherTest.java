package ${package}.client.message;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.doThrow;

import ${package}.client.api.test.Message;
import ${package}.client.message.config.TestMessageProducerConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class TestMessagePublisherTest {

    @MockBean
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TestMessagePublisher testMessagePublisher;

    @Test
    public void publish() throws Exception {
        testMessagePublisher.publish(new Message("test"));
    }

    @Test
    public void publishExceptionalState() throws Exception {
        doThrow(new RuntimeException()).when(rabbitTemplate).convertAndSend(any());

        testMessagePublisher.publish(new Message("test"));
    }

    @Configuration
    @EnableAutoConfiguration
    @EnableHystrix
    static class Config extends TestMessageProducerConfig {
    }
}