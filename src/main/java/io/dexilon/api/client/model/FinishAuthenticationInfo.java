package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

@Getter
public class FinishAuthenticationInfo {

    private final String accessToken;
    private final String refreshToken;


    public FinishAuthenticationInfo(@JsonProperty("accessToken") String accessToken, @JsonProperty("refreshToken") String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
