package carnivalOfMonsters.meta.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Request {
    @SuppressWarnings("FieldCanBeLocal")
    @JsonProperty
    private final String requestType;

    protected Request(String requestType) {
        this.requestType = requestType;
    }
}
