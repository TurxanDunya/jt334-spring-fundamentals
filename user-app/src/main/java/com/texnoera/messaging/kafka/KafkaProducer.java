package com.texnoera.messaging.kafka;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class KafkaProducer {

    public static final String HEADER_TYPE_ID = "__TypeId__";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    public void send(String topic, Object payload, MessageType type) {
        send(topic, UUID.randomUUID().toString(), payload,
                Map.of(HEADER_TYPE_ID, type.name()));
    }

    private void send(String topic, String key, Object payload, Map<String, String> headers) {
        ProducerRecord<String, Object> producerRecord = new ProducerRecord<>(topic, key, payload);
        if (headers != null) {
            headers.forEach((k, v) -> producerRecord.headers().add(k, v.getBytes()));
        }
        kafkaTemplate.send(producerRecord);
        log.info("User creation event sent successfully!");
    }

    @Getter
    public enum MessageType {
        USER_CREATION("UserCreationEvent");

        public final String value;

        MessageType(String value) {
            this.value = value;
        }

    }

}
