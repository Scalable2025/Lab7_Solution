package com.example.orders.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String SHIPPING_QUEUE = "shipping_queue";
    public static final String INVENTORY_QUEUE = "inventory_queue";
    public static final String EXCHANGE = "shared_exchange";
    public static final String SHIPPING_ROUTING_KEY = "shipping_routing_key";
    public static final String INVENTORY_ROUTING_KEY = "inventory_routing_key";

    @Bean(name = SHIPPING_QUEUE)
    public Queue shippingQueue() {
        return new Queue(SHIPPING_QUEUE);
    }

    @Bean(name = INVENTORY_QUEUE)
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding binding(@Qualifier(SHIPPING_QUEUE) Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(SHIPPING_ROUTING_KEY);
    }

    @Bean
    public Binding bindingInventory(@Qualifier(INVENTORY_QUEUE) Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(INVENTORY_ROUTING_KEY);
    }

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter());
        return rabbitTemplate;
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }
}

