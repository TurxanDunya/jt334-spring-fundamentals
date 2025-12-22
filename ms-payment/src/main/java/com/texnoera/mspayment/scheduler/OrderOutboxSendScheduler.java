package com.texnoera.mspayment.scheduler;

import com.texnoera.mspayment.client.MsPaymentClient;
import com.texnoera.mspayment.dao.OrderOutboxRepository;
import com.texnoera.mspayment.dao.entity.OrderOutboxEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@EnableScheduling
@RequiredArgsConstructor
public class OrderOutboxSendScheduler {

    private final OrderOutboxRepository orderOutboxRepository;
    private final MsPaymentClient msPaymentClient;

    @Scheduled(fixedRate = 5000)
    public void sendOrderOutbox() {
        OrderOutboxEntity orderOutboxEntity =
                orderOutboxRepository.getByStatus("IN_PROGRESS");

        // found 3 row

        msPaymentClient.pay();

        orderOutboxEntity.setStatus("SUCCESS");
        orderOutboxRepository.save(orderOutboxEntity);
    }

}
