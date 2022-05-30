package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class OrderBookInfo {

    private final List<OrderBookItemResponse> ask;
    private final List<OrderBookItemResponse> bid;

    public OrderBookInfo(@JsonProperty("ask") List<OrderBookItemResponse> ask, @JsonProperty("bid") List<OrderBookItemResponse> bid) {
        this.ask = ask;
        this.bid = bid;
    }

}
