package com.texnoera.mspayment.service;

import com.texnoera.mspayment.client.MsPaymentClient;
import com.texnoera.mspayment.dao.OrderOutboxRepository;
import com.texnoera.mspayment.dao.OrderRepository;
import com.texnoera.mspayment.dao.entity.OrderEntity;
import com.texnoera.mspayment.model.CreateOrderRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 1. Create order_outbox table
 */


@Service
@RequiredArgsConstructor
public class PaymentService {

    private final OrderRepository orderRepository;
    private final OrderOutboxRepository orderOutboxRepository;
    private final MsPaymentClient msPaymentClient;

    //@Transactional
    public void createOrder(CreateOrderRequestDto requestDto) {
        ExecutorService executor = Executors.newFixedThreadPool(1000);

        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    msPaymentClient.pay();
                } catch (Exception ex) {
                    // fallback is handled by Resilience4j automatically
                    System.out.println("Exception from pay(): " + ex.getMessage());
                }
            });
        }

        // Graceful shutdown
        executor.shutdown();
        // 1. requestDto -> map to orderEntity
        //OrderEntity orderEntity = orderRepository.save(orderEntity);

        // 2. orderEntity -> map to orderOutboxEntity
        //orderOutboxRepository.save(orderOutboxEntity); // IN_PROGRESS

        // logic - some codes
        // Exception happened
    }

}
