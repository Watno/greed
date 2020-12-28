package server.games;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import server.connections.IConnectionSender;

public class UserInGame implements IUserFromGamePerspective {
    private IConnectionSender connection;
    private String name;
    private JsonElement currentInput = null;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserInGame(IConnectionSender connection, String name) {
        super();
        this.connection = connection;
        this.name = name;
        objectMapper.registerModule(new Jdk8Module());
    }

    @Override
    public synchronized JsonElement requestInput(JsonObject request) {
        send(request);
        try {
            if (!hasResigned()) {
                wait();
                sendInputAcceptance();
            }
            JsonElement toReturn = currentInput;
            currentInput = null;
            return toReturn;
        } catch (InterruptedException e) {
            //awaiting Thread was cancelled
        }
        return null;
    }

    @Override
    public synchronized <T> T requestTypedInput(JsonNode request, TypeReference<T> requestedType) {
        while (true) {
            try {
                send(request);
                T toReturn = null;
                if (!hasResigned()) {
                    wait();
                    toReturn = objectMapper.readValue(currentInput.toString(), requestedType);
                    sendInputAcceptance();
                }
                currentInput = null;
                return toReturn;
            } catch (InterruptedException e) {
                //awaiting Thread was cancelled
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
        }
    }

    @Override
    public synchronized <T> T awaitTypedInput(TypeReference<T> requestedType) throws InterruptedException {
        while (true) {
            try {
                T toReturn = null;
                if (!hasResigned()) {
                    wait();
                    toReturn = objectMapper.readValue(currentInput.toString(), requestedType);
                    sendInputAcceptance();
                }
                currentInput = null;
                return toReturn;
            } catch (JsonMappingException e) {
                e.printStackTrace();
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            return null;
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
        if (!hasResigned()) {
            connection.send(json);
        }
    }

    @Override
    public void send(JsonNode json) {
        if (!hasResigned()) {
            connection.send(json);
        }
    }

    @Override
    public void send(Object object) {
        if (!hasResigned()) {
            connection.send(object);
        }
    }

    @Override
    public Boolean hasResigned() {
        return connection == null;
    }

    public synchronized void receiveInput(JsonElement json) {
        currentInput = json;
        notify();
    }

    public synchronized void resign() {
        connection = null;
        name += " (resigned)";
        notify();
    }

    private void sendInputAcceptance() {
        JsonObject json = new JsonObject();
        json.addProperty("request", "empty");
        json.addProperty("optional", false);
        json.addProperty("reason", "");
        send(json);
    }


}
