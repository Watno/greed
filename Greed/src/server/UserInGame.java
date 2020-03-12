package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UserInGame implements IUserFromGamePerspective {
    private User user;
    private String name;
    private JsonElement currentInput = null;
    private ObjectMapper objectMapper = new ObjectMapper();

    public UserInGame(User user) {
        super();
        this.user = user;
        this.name = user.getName();
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
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public synchronized <T> T requestTypedInput(JsonNode request, TypeReference<T> requestedType) {
        send(request);
        try {
            T toReturn = null;
            if (!hasResigned()) {
                wait();
                toReturn = objectMapper.readValue(currentInput.toString(), requestedType);
                sendInputAcceptance();
            }
            currentInput = null;
            return toReturn;
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
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
            user.send(json);
        }
    }

    @Override
    public void send(JsonNode json) {
        if (!hasResigned()) {
            user.send(json);
        }
    }

    @Override
    public Boolean hasResigned() {
        return user == null;
    }

    public synchronized void receiveInput(JsonElement json) {
        currentInput = json;
        notify();
    }

    public synchronized void resign() {
        user = null;
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
