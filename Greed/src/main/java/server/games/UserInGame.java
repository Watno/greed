package server.games;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import server.connections.IConnectionSender;
import server.connections.serialization.ObjectMapperProvider;

public class UserInGame implements IUserFromGamePerspective {
    private IConnectionSender connectionSender;
    private String name;
    private String currentInput = null;
    private final ObjectMapper objectMapper = ObjectMapperProvider.provide();

    public UserInGame(IConnectionSender connectionSender, String name) {
        super();
        this.connectionSender = connectionSender;
        this.name = name;
    }

    @Override
    public synchronized JsonElement requestInput(JsonObject request) throws DisconnectedException, InterruptedException {
        send(request);
        waitAndThrowIfDisconnected();
        JsonElement toReturn = JsonParser.parseString(currentInput);
        currentInput = null;
        return toReturn;
    }

    @Override
    public synchronized <T> T requestTypedInput(Object request, TypeReference<T> requestedType) throws DisconnectedException, InterruptedException {
        send(objectMapper.valueToTree(request));
        return awaitTypedInput(requestedType);
    }


    @Override
    public synchronized <T> T awaitTypedInput(TypeReference<T> requestedType) throws InterruptedException, DisconnectedException {
        waitAndThrowIfDisconnected();
        while(true) {
            try {
                var inputToProcess = currentInput;
                currentInput = null;
                return objectMapper.readValue(inputToProcess, requestedType);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void allowReturnToLobby() {
        JsonObject json = new JsonObject();
        json.addProperty("request", "returnToLobby");
        json.addProperty("optional", false);
        json.addProperty("reason", "Game Over");
        send(json);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void send(JsonObject json) {
        if (!isDisconnected()) {
            connectionSender.send(json);
        }
    }

    @Override
    public void send(JsonNode json) {
        if (!isDisconnected()) {
            connectionSender.send(json);
        }
    }

    @Override
    public void send(Object object) {
        if (!isDisconnected()) {
            connectionSender.send(object);
        }
    }

    @Override
    public Boolean isDisconnected() {
        return connectionSender == null;
    }

    public synchronized void receiveInput(String json) {
        currentInput = json;
        notify();
    }

    public synchronized void markAsDisconnected() {
        connectionSender = null;
        name += " (disconnected)";
        notify();
    }

    private void sendInputAcceptance() {
        JsonObject json = new JsonObject();
        json.addProperty("request", "empty");
        json.addProperty("optional", false);
        json.addProperty("reason", "");
        send(json);
    }

    private synchronized void waitAndThrowIfDisconnected() throws DisconnectedException, InterruptedException {
        if (isDisconnected()) throw new DisconnectedException();
        wait();
        if (isDisconnected()) throw new DisconnectedException();
        sendInputAcceptance();
    }


}
