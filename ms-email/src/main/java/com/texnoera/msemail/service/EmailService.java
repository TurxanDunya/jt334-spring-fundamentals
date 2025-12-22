package com.texnoera.msemail.service;

import com.texnoera.msemail.messaging.event.UserCreatedEvent;
import com.texnoera.msemail.messaging.publisher.UserDlqPublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final UserDlqPublisher userDlqPublisher;

    public void sendEmail(UserCreatedEvent event) {
        try {
            log.info("Sending email to: "
                    + event.getEmail()
                    + " with message: "
                    + event.getMessage());
            throw new Exception("Error sending email");
        } catch (Exception e) {
            userDlqPublisher.send(event);
        }
    }

}
