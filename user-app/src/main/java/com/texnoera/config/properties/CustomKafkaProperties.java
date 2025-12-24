package com.texnoera.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

import java.util.Map;

@Data
@Component
@RefreshScope
@ConfigurationProperties(prefix = "kafka")
public class CustomKafkaProperties {

    private Map<String, TopicConfig> topics;

    @Data
    public static class TopicConfig {
        private String name;
        private int partitions;
        private int replicas;
        private String minInSyncReplicas;
    }

}
