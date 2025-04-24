package com.example.shipping;

import com.example.shipping.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class ShippingService {

    @RabbitListener(queues = RabbitMQConfig.SHIPPING_QUEUE)
    public void notifyShipping(Shipping shipping) {
        System.out.println("Received Shipping Order: " + shipping.getId() + " from "+shipping.getName());
    }
}
