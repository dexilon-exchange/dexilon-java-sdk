package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import java.util.List;

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class AllSymbolResponse extends GenericResponse {

    private final List<SymbolItem> body;

    public AllSymbolResponse(@JsonProperty("body") List<SymbolItem> body, @JsonProperty("errorBody") String errorBody, @JsonProperty("debugInfo") String debugInfo) {
        super(errorBody, debugInfo);
        this.body = body;
    }
}
