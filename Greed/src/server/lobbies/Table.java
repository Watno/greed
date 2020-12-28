package server.lobbies;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import server.games.IGameFactory;
import server.games.IUserFromGamePerspective;

import java.util.ArrayList;


public class Table {
    private ArrayList<LobbyUser> players = new ArrayList<>();
    private int numberOfPlayers = 3;
    private Lobby lobby;
    private IGameFactory gameFactory;

    public Table(IGameFactory gameFactory, LobbyUser player, Lobby lobby) {
        this.gameFactory = gameFactory;
        addPlayer(player);
        this.lobby = lobby;
    }

    public void startGame() {
        unreadyPlayers();
        ArrayList<IUserFromGamePerspective> usersFromGamePerspective = new ArrayList<>();
        for (LobbyUser lobbyUser : players) {
            usersFromGamePerspective.add(lobbyUser.joinGame());
        }
        new Thread(gameFactory
                .createGame(usersFromGamePerspective, numberOfPlayers))
                .start();
        lobby.removeTable(this);
    }

    public boolean addPlayer(LobbyUser player) {
        if (players.size() < numberOfPlayers) {
            unreadyPlayers();
            players.add(player);
            if (player.getTable() != null) {
                player.getTable().removePlayer(player);
            }
            player.setTable(this);
            return true;
        }
        return false;
    }

    public void removePlayer(LobbyUser player) {
        players.remove(player);
        player.setTable(null);
        if (players.size() == 0) {
            lobby.removeTable(this);
        }
        unreadyPlayers();
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        if (numberOfPlayers >= 2 && numberOfPlayers <= 5 && numberOfPlayers >= players.size()) {
            this.numberOfPlayers = numberOfPlayers;
            unreadyPlayers();
        }
    }

    public ArrayList<LobbyUser> getPlayers() {
        return players;
    }

    public JsonObject toJSON() {
        JsonObject json = new JsonObject();
        json.addProperty("numberOfPlayers", numberOfPlayers);
        JsonArray JsonPlayers = new JsonArray();
        for (LobbyUser player : players) {
            JsonPlayers.add(player.getName());
        }
        json.add("players", JsonPlayers);
        return json;
    }

    private void unreadyPlayers() {
        for (LobbyUser player : players) {
            player.setReady(false);
        }
    }

    public void sendChat(JsonObject json) {
        for (LobbyUser connection : players) {
            connection.send(json);
        }
    }

}
