package io.dexilon.api.client.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor(onConstructor = @__(@JsonCreator))
@Getter
public class GenericResponse {

    private final String errorBody;
    private final String debugInfo;

}
