package com.microservices.fraud;

import com.microservices.clients.fraud.FraudCheckResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/fraud-check")
public class FraudController {

    FraudCheckService fraudCheckService;

    @Autowired
    public FraudController(FraudCheckService fraudCheckService)
    {
        this.fraudCheckService = fraudCheckService;
    }

    @GetMapping("/{customerId}")
    public FraudCheckResponse isFraudster(@PathVariable("customerId") Integer customerID) {
        boolean isFraudster = fraudCheckService.isFraudulentCustomer(customerID);
        //log.info("fraud check request for customer: {}", customerID);
        return new FraudCheckResponse(isFraudster);
    }
}
