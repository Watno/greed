package carnivalOfMonsters.meta.requests;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class Request {
    @JsonProperty
    private String requestType;

    protected Request(String requestType) {
        this.requestType = requestType;
    }
}
