package com.texnoera.mspayment.client;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MsPaymentClient {

    private static Integer COUNT = 0;
    private static final Integer MAX_COUNT = 3;

    @Bulkhead(name = "PaymentClientBH", fallbackMethod = "callErrorPagePayment")
    @CircuitBreaker(name = "PaymentClientCB", fallbackMethod = "callErrorPagePayment")
    public void pay() {
        log.info("Payment pay called");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // ms-payment called
//        if (COUNT >= MAX_COUNT) {
//            COUNT = 0;
//            throw new RuntimeException("Payment failed");
//        } else {
//            COUNT++;
//        }
        log.info("Payment completed");
    }

    public void callErrorPagePayment(Throwable t) {
        // ms-payment-error called
        log.info("Payment failed page called");
    }

}
