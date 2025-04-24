package com.example.inventory;

import com.example.inventory.rabbitmq.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class InventoryService {

    @RabbitListener(queues = RabbitMQConfig.INVENTORY_QUEUE)
    public void checkInventory(@PathVariable int amount) {
        // Simulate checking inventory
        if (amount <= 5) {
            System.out.println("Amount is fulfilled");
        }
        else {
            System.out.println("Insufficient Inventory");
        }
    }
}
