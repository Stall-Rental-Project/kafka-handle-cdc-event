package com.srs.handle_cdc_event.kafka.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SyncCommonKafkaMessage<T> {
    @JsonProperty("before")
    public T before;

    @JsonProperty("after")
    public T after;

    @JsonProperty("source")
    public SourceKafkaDTO source;

    @JsonProperty("op")
    public String op;

    @JsonProperty("transaction")
    public TransactionKafkaDTO transaction;

    @JsonProperty("ts_ms")
    public long tsMs;
}
