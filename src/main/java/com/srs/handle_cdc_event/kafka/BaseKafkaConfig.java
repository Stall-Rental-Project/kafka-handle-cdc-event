package com.srs.handle_cdc_event.kafka;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

public abstract class BaseKafkaConfig {
    /**
     * This requires <code>spring.kafka.bootstrap-servers</code> and <code>spring.kafka.consumer.group-id</code> set to work
     *
     * @param kafkaProperties injected environment Kafka-related properties
     * @return default consumer config map
     */


    protected Map<String,Object> defaultConsumerConfig(KafkaProperties kafkaProperties){
        Map<String,Object> configMap = new HashMap<>();
        configMap.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        configMap.put(ConsumerConfig.GROUP_ID_CONFIG, kafkaProperties.getConsumer().getGroupId());

        configMap.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);
        configMap.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class);

        configMap.put(ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class);
        configMap.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);

        configMap.put(JsonDeserializer.TRUSTED_PACKAGES, "com.srs.handle_cdc_event.*");
        configMap.put(JsonDeserializer.VALUE_DEFAULT_TYPE,"com.srs.handle_cdc_event.kafka.message.SyncCommonKafkaMessage");
        return configMap;
    }

    /**
     * This requires <code>spring.kafka.bootstrap-servers</code> set to work
     *
     * @param kafkaProperties injected environment Kafka-related properties
     * @return default producer config map
     */
    protected Map<String, Object> defaultProducerConfig(KafkaProperties kafkaProperties) {
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());

        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class);

        return configMap;
    }
}
