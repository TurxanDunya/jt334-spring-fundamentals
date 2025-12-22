package com.texnoera.msemail.messaging.listener;

import com.texnoera.msemail.messaging.event.UserCreatedEvent;
import com.texnoera.msemail.service.EmailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class UserListener {

    private final EmailService emailService;

    @RabbitListener(queues = "${rabbitmq.queue.user_created}")
    public void createdUser(UserCreatedEvent event) {
        if (event.getMessage().contains("success")) {
            emailService.sendEmail(event);
        }
    }

}
