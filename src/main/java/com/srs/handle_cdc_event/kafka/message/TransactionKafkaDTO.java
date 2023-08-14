package com.srs.handle_cdc_event.kafka.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransactionKafkaDTO {
    @JsonProperty("id")
    public String id;

    @JsonProperty("total_order")
    public int totalOrder;

    @JsonProperty("data_collection_order")
    public int dataCollectionOrder;
}
