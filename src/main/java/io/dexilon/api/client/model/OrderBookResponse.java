package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;


@Getter
@ToString
public class OrderBookResponse extends GenericResponse {

    private final OrderBookInfo body;

    public OrderBookResponse(@JsonProperty("errorBody") String errorBody, @JsonProperty("debugInfo") String debugInfo, @JsonProperty("body") OrderBookInfo body) {
        super(errorBody, debugInfo);
        this.body = body;
    }
}
