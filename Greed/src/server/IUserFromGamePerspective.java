package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public interface IUserFromGamePerspective {

    JsonElement requestInput(JsonObject request);

    void allowReturnToLobby();

    String getName();

    void send(JsonObject json);

    Boolean hasResigned();

}