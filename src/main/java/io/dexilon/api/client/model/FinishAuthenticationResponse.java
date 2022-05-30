package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FinishAuthenticationResponse extends GenericResponse {

    private final FinishAuthenticationInfo body;

    public FinishAuthenticationResponse(@JsonProperty("errorBody") String errorBody,  @JsonProperty("debugInfo") String debugInfo,  @JsonProperty("body") FinishAuthenticationInfo body) {
        super(errorBody, debugInfo);
        this.body = body;
    }
}
