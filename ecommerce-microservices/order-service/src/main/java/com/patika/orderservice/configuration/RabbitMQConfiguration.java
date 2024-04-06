package com.patika.orderservice.configuration;

import lombok.Data;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Data
public class RabbitMQConfiguration {

    @Value("${rabbitmq.notification.queue}")
    private String queueName;

    @Value("${rabbitmq.stock.queue}")
    private String stockQueueName;

    @Value("${rabbitmq.invoice.queue}")
    private String invoiceQueueName;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.notification.routingkey}")
    private String routingkey;

    @Value("${rabbitmq.stock.routingkey}")
    private String stockRoutingkey;

    @Value("${rabbitmq.invoice.routingkey}")
    private String invoiceRoutingkey;

    @Bean
    @Qualifier("notificationQueue")
    public Queue queue() {
        return new Queue(queueName, false);
    }

    @Bean
    @Qualifier("stockQueue")
    public Queue stockQueue() {
        return new Queue(stockQueueName, false);
    }

    @Bean
    @Qualifier("invoiceQueue")
    public Queue invoiceQueue() {
        return new Queue(invoiceQueueName, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange);
    }

    @Bean
    public Binding binding(@Qualifier("notificationQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(routingkey);
    }

    @Bean
    public Binding stockBinding(@Qualifier("stockQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(stockRoutingkey);
    }

    @Bean
    public Binding invoiceBinding(@Qualifier("invoiceQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(invoiceRoutingkey);
    }

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public AmqpTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(jsonMessageConverter());
        return rabbitTemplate;
    }
}
