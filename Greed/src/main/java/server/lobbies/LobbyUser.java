package server.lobbies;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import server.connections.IConnectionSender;
import server.games.UserInGame;

import java.time.LocalDateTime;

public class LobbyUser {
    private final IConnectionSender connection;
    private String name = "defaultname";
    private Table table = null;
    private boolean ready = false;
    private UserInGame userInGame = null;

    public LobbyUser(IConnectionSender connection) {
        this.connection = connection;
    }

    public void resign() {
        if (table != null) {
            table.removePlayer(this);
        }
        if (userInGame != null) {
            userInGame.resign();
        }
    }

    public void forwardToGame(JsonElement json) {
        if (userInGame != null) {
            userInGame.receiveInput(json.toString());
        }
    }

    public void send(JsonObject json) {
        connection.send(json);
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        System.out.println(LocalDateTime.now() + " - " + this.name + " changes their name to " + name);
        this.name = name;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
        if (ready) {
            boolean starting = true;
            for (LobbyUser player : table.getPlayers()) {
                starting &= player.isReady();
            }
            if (starting) {
                table.startGame();
            }
        }
    }

    public UserInGame joinGame() {
        this.userInGame = new UserInGame(connection, this.name);
        return this.userInGame;
    }

}
