package com.kafka.provider.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig {

    @Bean
    public NewTopic generateTopic() {

        Map<String, String> configuration = new HashMap<>();
        configuration.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // Cuando se envia el mismo mensaje -- Delete: Borra el mensaje Compact: Mantiene el más actualizado
        configuration.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // El mensaje se retiene durante 1 día (86400000 milisegundos) aunque se envie varias veces el mismo mensaje - Por defecto: -1, no se borran los mensajes.
        configuration.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Tamaño máximo del segmento. 1073741824 bytes == 1Gb.
        configuration.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "4194304"); // Tamaño máximo de cada mensaje. Por defecto 1048576 == 1 MB.


        return TopicBuilder.name("example-name-Topic")
                .partitions(2)
                .replicas(1)
                .configs(configuration)
                .build();

    }

}
