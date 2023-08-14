package com.srs.handle_cdc_event.kafka.message;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SourceKafkaDTO {
    @JsonProperty("version")
    public String version;

    @JsonProperty("connector")
    public String connector;

    @JsonProperty("name")
    public String name;

    @JsonProperty("ts_ms")
    public long tsMs;

    @JsonProperty("snapshot")
    public String snapShot;

    @JsonProperty("db")
    public String db;

    @JsonProperty("sequence")
    public String sequence;

    @JsonProperty("schema")
    public String schema;

    @JsonProperty("table")
    public String table;

    @JsonProperty("txId")
    public int txId;

    @JsonProperty("lsn")
    public long lsn;

    @JsonProperty("xmin")
    public String xMin;
}
