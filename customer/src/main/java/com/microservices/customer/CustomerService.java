package com.microservices.customer;

import com.microservices.customer.rabbitmq.RabbitMQMessageProducer;
import com.microservices.customer.clients.fraud.FraudCheckResponse;
import com.microservices.customer.clients.notification.NotificationRequest;
import com.microservices.customer.clients.fraud.FraudClient;
import com.microservices.customer.clients.notification.NotificationClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CustomerService {
    CustomerRepository customerRepository;
    RestTemplate restTemplate;
    FraudClient fraudClient;
    NotificationClient notificationClient;
    RabbitMQMessageProducer rabbitMQMessageProducer;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate, FraudClient fraudClient,NotificationClient notificationClient, RabbitMQMessageProducer rabbitMQMessageProducer) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
        this.rabbitMQMessageProducer = rabbitMQMessageProducer;
    }

    public void registerCustomer(CustomerRegistrationRequest request) {

        Customer customer = new Customer(request.getFirstName(), request.getLastName(), request.getEmail());
        // todo: check if email valid
        // todo: check if email not taken
        // todo: check if fraudster

        customerRepository.saveAndFlush(customer);


        FraudCheckResponse fraudCheckResponse = fraudClient.isFraudster(customer.getId());

        if(fraudCheckResponse.isFraudster())
        {
            throw new IllegalStateException("fraudster");
        }

        NotificationRequest notificationRequest = new NotificationRequest(
                customer.getId(),
                customer.getEmail(),
                String.format("Hi %s, welcome to Microservices...",
                        customer.getFirstName()));

        //notificationClient.sendNotification(notificationRequest);

        rabbitMQMessageProducer.publish(
                notificationRequest,
                "internal.exchange",
                "internal.notification.routing-key"
        );
    }
}
