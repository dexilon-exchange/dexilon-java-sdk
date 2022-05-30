package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class SymbolItem {

    private final String symbol;
    private final Boolean isFavorite;
    private final BigDecimal lastPrice;
    private final BigDecimal volume;
    private final BigDecimal price24Percentage;


    public SymbolItem(@JsonProperty("symbol") String symbol,
                      @JsonProperty("isFavorite") Boolean isFavorite,
                      @JsonProperty("lastPrice") BigDecimal lastPrice,
                      @JsonProperty("volume") BigDecimal volume,
                      @JsonProperty("price24Percentage") BigDecimal price24Percentage) {
        this.symbol = symbol;
        this.isFavorite = isFavorite;
        this.lastPrice = lastPrice;
        this.volume = volume;
        this.price24Percentage = price24Percentage;
    }
}
