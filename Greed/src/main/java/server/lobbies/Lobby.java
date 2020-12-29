package server.lobbies;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import server.games.IGameFactory;

import java.util.ArrayList;
import java.util.Map;

public class Lobby {
    private ArrayList<Table> tables = new ArrayList<>();
    private ArrayList<LobbyUser> connections = new ArrayList<>();
    private Map<String, IGameFactory> gameFactories;

    public Lobby(Map<String, IGameFactory> gameFactories) {
        this.gameFactories = gameFactories;
    }

    public void makeTable(LobbyUser player, String gameName) {
        tables.add(new Table(gameFactories.get(gameName), player, this));
    }

    public void removeTable(Table table) {
        tables.remove(table);
        for (LobbyUser connection : table.getPlayers()) {
            removeConnection(connection);
        }
    }

    public void removeConnection(LobbyUser connection) {
        connections.remove(connection);
    }

    public void addConnection(LobbyUser connection) {
        connections.add(connection);
    }

    public void sendJSON() {
        JsonObject json = new JsonObject();
        JsonArray jsonTables = new JsonArray();
        for (Table table : tables) {
            jsonTables.add(table.toJSON());
        }
        json.add("tables", jsonTables);
        json.addProperty("type", "lobby");
        for (LobbyUser connection : connections) {
            if (connection.getTable() != null) {
                json.addProperty("tablenumber", tables.indexOf(connection.getTable()));
            } else {
                json.remove("tablenumber");
            }
            connection.send(json);
        }
    }

    public void sendChat(JsonObject chat) {
        for (LobbyUser connection : connections) {
            connection.send(chat);
        }
    }

    public ArrayList<Table> getTables() {
        return tables;
    }
}
