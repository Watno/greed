package server.connections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.webbitserver.WebSocketConnection;
import server.connections.serialization.ObjectMapperProvider;

public class WebsocketConnectionSender implements IConnectionSender {
    private final WebSocketConnection connection;
    private final ObjectMapper serializer = ObjectMapperProvider.provide();


    public WebsocketConnectionSender(WebSocketConnection connection) {
        this.connection = connection;
    }

    @Override
    public void send(JsonObject json) {
        connection.send(new GsonBuilder().setPrettyPrinting().create().toJson(json));
    }

    @Override
    public void send(JsonNode json) {
        connection.send(json.toPrettyString());
    }

    @Override
    public void send(Object object) {
        send(serializer.valueToTree(object));
    }
}
