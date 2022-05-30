package io.dexilon.api.client.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class MetamaskAddressRequest {

    private final String metamaskAddress;

}
