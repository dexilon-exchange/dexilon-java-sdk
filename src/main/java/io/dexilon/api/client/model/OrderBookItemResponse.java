package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@EqualsAndHashCode
@ToString
public class OrderBookItemResponse {

    private final BigDecimal price;
    private final BigDecimal size;
    private final BigDecimal sum;

    public OrderBookItemResponse(@JsonProperty("price") BigDecimal price, @JsonProperty("size") BigDecimal size, @JsonProperty("sum") BigDecimal sum) {
        this.price = price;
        this.size = size;
        this.sum = sum;
    }
}
