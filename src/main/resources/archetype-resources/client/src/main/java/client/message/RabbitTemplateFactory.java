package ${package}.client.message;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.util.Assert;

//TODO: remove
public class RabbitTemplateFactory {

    private String exchange;
    private String routingKey;

    public RabbitTemplateFactory() {
    }

    public RabbitTemplateFactory(String exchange, String routingKey) {
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    protected RabbitTemplate createRabbitTemplate(ConnectionFactory connectionFactory) {
        Assert.notNull(exchange);
        Assert.notNull(routingKey);

        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        rabbitTemplate.setExchange(exchange);
        rabbitTemplate.setRoutingKey(routingKey);
        return rabbitTemplate;
    }

    public String getExchange() {
        return exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getRoutingKey() {
        return routingKey;
    }

    public void setRoutingKey(String routingKey) {
        this.routingKey = routingKey;
    }
}
