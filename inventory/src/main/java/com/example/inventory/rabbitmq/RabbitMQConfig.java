package com.example.inventory.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String INVENTORY_QUEUE = "inventory_queue";
    public static final String EXCHANGE = "shared_exchange";
    public static final String INVENTORY_ROUTING_KEY = "inventory_routing_key";


    @Bean(name = INVENTORY_QUEUE)
    public Queue inventoryQueue() {
        return new Queue(INVENTORY_QUEUE);
    }

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Binding bindingInventory(@Qualifier(INVENTORY_QUEUE) Queue queue, TopicExchange exchange) {
        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(INVENTORY_ROUTING_KEY);
    }
}

