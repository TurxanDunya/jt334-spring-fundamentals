package com.texnoera.config;

import com.texnoera.config.properties.CustomKafkaProperties;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import static com.texnoera.config.properties.KafkaConstants.USER_CREATION_TOPIC;
import static org.apache.kafka.common.config.TopicConfig.MIN_IN_SYNC_REPLICAS_CONFIG;

@Configuration
@RequiredArgsConstructor
public class KafkaConfig {

    private final CustomKafkaProperties kafkaProperties;

    @Bean
    public NewTopic userCreationTopic() {
        CustomKafkaProperties.TopicConfig topic =
                kafkaProperties.getTopics().get(USER_CREATION_TOPIC);

        return TopicBuilder.name(topic.getName())
                .partitions(topic.getPartitions())
                .replicas(topic.getReplicas())
                .config(MIN_IN_SYNC_REPLICAS_CONFIG, topic.getMinInSyncReplicas())
                .build();
    }

}
