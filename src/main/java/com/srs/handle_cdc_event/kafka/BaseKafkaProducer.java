package com.srs.handle_cdc_event.kafka;

import org.slf4j.Logger;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

public abstract class BaseKafkaProducer {
    protected final KafkaTemplate<String, Object> kafkaTemplate;

    public BaseKafkaProducer(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    protected  void sendThenLogResult(final String topic, Object message, Logger log){
        ListenableFuture<SendResult<String, Object>> future= kafkaTemplate.send(topic,message);
        future.addCallback(new ListenableFutureCallback<>() {
            @Override
            public void onFailure(Throwable ex) {
                log.error("Failed to send message to topic. {} - {}", ex.getClass().getSimpleName(), ex.getMessage());
            }

            @Override
            public void onSuccess(SendResult<String, Object> result) {
                log.info("Succeed to send message to topic {}", result.getRecordMetadata().topic());
            }
        });
    }
}
