package com.srs.handle_cdc_event.kafka.consumer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.srs.handle_cdc_event.kafka.message.NotificationViewKafkaMessage;
import com.srs.handle_cdc_event.kafka.message.SyncCommonKafkaMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
@KafkaListener(topics = {
        "account.emarket.notification_view"
}, containerFactory = "CustomKafkaListenerContainerFactory")
@RequiredArgsConstructor
@Log4j2
public class DebeziumKafkaConsumer {
    private final ObjectMapper objectMapper;

    @KafkaHandler
    public void doSendEmail(@Payload SyncCommonKafkaMessage<NotificationViewKafkaMessage> map) {

        try {
            log.info("Received "+objectMapper.writeValueAsString(map));
        } catch (Exception e) {
            log.error("Failed to send email out to user after application payment get submitted. {} - {}", e.getClass().getSimpleName(), e.getMessage());
            e.printStackTrace();
        }
    }
    @KafkaHandler(isDefault = true)
    public void unrecognizedMessage(Object unrecognizedMessage) {
        try {
            log.warn("Unrecognized message found with type {} and content {}",
                    unrecognizedMessage.getClass().getSimpleName(),
                    objectMapper.writeValueAsString(unrecognizedMessage));
        } catch (JsonProcessingException e) {
            log.error("Error when write message to string. {}", e.getMessage());
            log.warn("Unrecognized malformed message found with type {}",
                    unrecognizedMessage.getClass().getSimpleName());
        }
    }
}
