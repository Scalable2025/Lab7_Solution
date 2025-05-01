package com.microservices.fraud.clients.notification;


public class NotificationRequest {
    Integer toCustomerId;
    String toCustomerName;
    String message;

    public NotificationRequest() {}

    public NotificationRequest(Integer toCustomerId, String toCustomerName, String message) {
        this.toCustomerId = toCustomerId;
        this.toCustomerName = toCustomerName;
        this.message = message;
    }

    public Integer getToCustomerId() {
        return toCustomerId;
    }

    public void setToCustomerId(Integer toCustomerId) {
        this.toCustomerId = toCustomerId;
    }

    public String getToCustomerName() {
        return toCustomerName;
    }

    public void setToCustomerName(String toCustomerName) {
        this.toCustomerName = toCustomerName;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
