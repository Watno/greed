package server.connections;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import org.webbitserver.WebSocketConnection;

public class WebsocketConnectionSender implements IConnectionSender {
    private final WebSocketConnection connection;
    private final ObjectMapper serializer = new ObjectMapper();


    public WebsocketConnectionSender(WebSocketConnection connection) {
        this.connection = connection;
        serializer.registerModule(new Jdk8Module());
        serializer.configure(MapperFeature.AUTO_DETECT_GETTERS, false);
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
