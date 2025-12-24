package com.texnoera.msemail.messaging.kafka;

import com.texnoera.msemail.messaging.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class TariffSubscriptionListener {

    @KafkaListener(id = "onUserCreated",
            topics = "${kafka.topics.user_creation.name}",
            groupId = "${kafka.consumer-group}",
            clientIdPrefix = "jt334.")
    public void onUserCreated(UserCreatedEvent event) {
        log.info("Event consumed: {}", event); 
    }

}
