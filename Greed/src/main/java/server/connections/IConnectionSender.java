package server.connections;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;

public interface IConnectionSender {
    void send(JsonObject json);

    void send(JsonNode json);

    void send(Object object);
}
