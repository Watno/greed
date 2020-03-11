package server;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface IUserFromGamePerspective {

    JsonElement requestInput(JsonObject request);

    <T> T requestTypedInput(ObjectNode request);

    void allowReturnToLobby();

    String getName();

    void send(JsonObject json);

    void send(ObjectNode json);

    Boolean hasResigned();

}