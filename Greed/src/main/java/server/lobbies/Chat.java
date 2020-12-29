package server.lobbies;

import com.google.gson.JsonObject;

import java.time.LocalDateTime;

public class Chat {
    private final Lobby lobby;

    public Chat(Lobby lobby) {
        this.lobby = lobby;
    }

    public void handleMessage(JsonObject receivedMessage, LobbyUser connection) {
        JsonObject json = makeJSON(receivedMessage.get("chat").getAsString(), connection);
        if (receivedMessage.get("location").getAsString().equals("lobby")) {
            sendToLobby(json);
        }
        if (receivedMessage.get("location").getAsString().equals("table")) {
            sendToTable(json, connection.getTable());
        }
    }

    private JsonObject makeJSON(String message, LobbyUser connection) {
        JsonObject json = new JsonObject();
        json.addProperty("type", "chat");
        json.addProperty("message", message);
        json.addProperty("sender", connection.getName());
        System.out.println(LocalDateTime.now() + " - " + connection.getName() + ": " + message);
        return json;
    }

    private void sendToLobby(JsonObject json) {
        json.addProperty("location", "lobby");
        lobby.sendChat(json);
    }

    private void sendToTable(JsonObject json, Table table) {
        json.addProperty("location", "table");
        table.sendChat(json);
    }

}
