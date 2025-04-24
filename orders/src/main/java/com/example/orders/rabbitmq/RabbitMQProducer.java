package com.example.orders.rabbitmq;

import com.example.orders.dto.Shipping;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendToShipping(Shipping shipping) {
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.SHIPPING_ROUTING_KEY,
                shipping
        );
        System.out.println("Sent From Cart: " + shipping.getId()+" to "+shipping.getName());
    }

    public void sendToInventory(int amount) {

        rabbitTemplate.convertAndSend(
                RabbitMQConfig.EXCHANGE,
                RabbitMQConfig.INVENTORY_ROUTING_KEY,
                amount
        );
        System.out.println("Sent From Cart: " + amount);
    }
}
