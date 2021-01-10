package server.connections.testing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import server.connections.IConnectionSender;
import server.connections.serialization.ObjectMapperProvider;

public class MockConnectionSender implements IConnectionSender {
    private final ObjectMapper serializer = ObjectMapperProvider.provide();

    @Override
    public void send(JsonObject json) {
        System.out.println(new GsonBuilder().setPrettyPrinting().create().toJson(json));
    }

    @Override
    public void send(JsonNode json) {
        System.out.println(json.toPrettyString());
    }

    @Override
    public void send(Object object) {
        send(serializer.valueToTree(object));
    }
}
