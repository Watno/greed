package server.games;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface IUserFromGamePerspective {

    JsonElement requestInput(JsonObject request);

    <T> T requestTypedInput(JsonNode request, TypeReference<T> requestedType);

    <T> T awaitTypedInput(TypeReference<T> requestedType) throws InterruptedException;

    void allowReturnToLobby();

    String getName();

    void send(JsonObject json);

    void send(JsonNode json);

    void send(Object object);

    Boolean hasResigned();

}