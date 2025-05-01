package com.microservices.notification;

import com.microservices.clients.notification.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {
    NotificationRepository notificationRepository;

    @Autowired
    public NotificationService(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    public void send(NotificationRequest notificationRequest) {
        notificationRepository.save(new Notification(notificationRequest.getToCustomerId(),notificationRequest.getToCustomerName(),"Mado",notificationRequest.getMessage(),LocalDateTime.now()));
    }
}
