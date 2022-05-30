package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class StartAuthResponse extends GenericResponse {

    private final String nonce;

    public StartAuthResponse(@JsonProperty("nonce") String nonce, @JsonProperty("errorBody") String errorBody, @JsonProperty("debugInfo") String debugInfo) {
        super(errorBody, debugInfo);
        this.nonce = nonce;
    }
}
