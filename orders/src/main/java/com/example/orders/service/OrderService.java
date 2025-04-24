package com.example.orders.service;

import com.example.orders.clients.InventoryClient;
import com.example.orders.dto.Shipping;
import com.example.orders.rabbitmq.RabbitMQProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class OrderService {

    RabbitMQProducer rabbitMQProducer;
    InventoryClient inventoryClient;

    @Autowired
    public OrderService(RabbitMQProducer rabbitMQProducer, InventoryClient inventoryClient) {
        this.rabbitMQProducer = rabbitMQProducer;
        this.inventoryClient = inventoryClient;
    }

    public String createOrder(int amount) {

        rabbitMQProducer.sendToInventory(amount);

        // todo: Send To Shipping

        Shipping shipping = new Shipping(UUID.randomUUID().toString(),"Bolt");

        rabbitMQProducer.sendToShipping(shipping);

        return "Order created successfully!";
    }
}
