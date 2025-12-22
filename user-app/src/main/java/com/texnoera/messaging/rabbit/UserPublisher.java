package com.texnoera.messaging.rabbit;

import com.texnoera.config.RabbitConfig;
import com.texnoera.messaging.rabbit.event.UserCreatedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class UserPublisher {

    private final RabbitTemplate rabbitTemplate;

    public void send(UserCreatedEvent event) {
        log.info("Sending event: {}", event);
        rabbitTemplate.convertAndSend(
                RabbitConfig.EXCHANGE_NAME,
                RabbitConfig.ROUTING_KEY,
                event);
        log.info("Event sent");
    }

}
