package com.srs.handle_cdc_event.kafka.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationViewKafkaMessage {
    @JsonProperty("external_id")
    private String externalId;


    @JsonProperty("view_id")
    private String viewId;
}
