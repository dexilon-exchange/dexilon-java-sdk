package io.dexilon.api.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Builder
public class FinishAuthenticationRequest {

    private final String metamaskAddress;
    private final String signedNonce;

}
