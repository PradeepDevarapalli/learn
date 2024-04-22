package com.learnspring.employee.kafka.configs;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;


import java.util.Collections;
import java.util.Map;

@Configuration
public class KafkaConfig {
    @Bean
    NewTopic createTopic() {
        return TopicBuilder.name("product-created-events-topic")
                .partitions(3)
                .replicas(1)
                .configs(Map.of("min.insync.replicas","2"))
                .build();
    }
}
