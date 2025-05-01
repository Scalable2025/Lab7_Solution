package com.microservices.customer.clients.fraud;


public class FraudCheckResponse {
    boolean isFraudster;

    public FraudCheckResponse() {
    }

    public FraudCheckResponse(boolean isFraudster) {
        this.isFraudster = isFraudster;
    }

    public boolean isFraudster() {
        return isFraudster;
    }

    public void setFraudster(boolean fraudster) {
        isFraudster = fraudster;
    }
}
