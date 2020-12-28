package server.connections.testing;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import server.connections.IConnectionSender;

public class MockConnectionSender implements IConnectionSender {
    private final ObjectMapper serializer = new ObjectMapper();

    public MockConnectionSender() {
        serializer.registerModule(new Jdk8Module());
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
    }

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
