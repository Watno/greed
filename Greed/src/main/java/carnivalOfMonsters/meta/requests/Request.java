package carnivalOfMonsters.meta.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Request {
    @JsonProperty
    private final String requestType;

    protected Request(String requestType) {
        this.requestType = requestType;
    }
}
