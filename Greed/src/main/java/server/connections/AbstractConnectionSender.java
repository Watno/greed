package server.connections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import server.connections.serialization.ObjectMapperProvider;

public abstract class AbstractConnectionSender implements IConnectionSender {
    private final ObjectMapper serializer = ObjectMapperProvider.provide();

    @Override
    public void send(JsonObject json) {
        send(new GsonBuilder().setPrettyPrinting().create().toJson(json));
    }

    @Override
    public void send(JsonNode json) {
        send(json.toPrettyString());
    }

    @Override
    public void send(Object object) {
        send(serializer.valueToTree(object));
    }

    protected abstract void send(String message);
}
