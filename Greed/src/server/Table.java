package server;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;


public class Table {
	private ArrayList<User> players = new ArrayList<User>();
	private int numberOfPlayers=3;
	private Lobby lobby; 
	private IGameFactory gameFactory;
	
	public Table(IGameFactory gameFactory, User player, Lobby lobby) {
		this.gameFactory = gameFactory;
		addPlayer(player);
		this.lobby=lobby;
	}
	
	public void startGame() {
		unreadyPlayers();
		new Thread(gameFactory
			.createGame(players, numberOfPlayers))
			.start();
		lobby.removeTable(this);
	}
	
	public boolean addPlayer(User player) {
		if (players.size()<numberOfPlayers) {
			unreadyPlayers();
			players.add(player);
			if (player.getTable()!=null) {
				player.getTable().removePlayer(player);
			}
			player.setTable(this);
			return true;
		}
		return false;
	}
	
	public void removePlayer(User player) {
		players.remove(player);
		player.setTable(null);
		if (players.size()==0) {
			lobby.removeTable(this);
		}
		unreadyPlayers();
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		if (numberOfPlayers>=2 && numberOfPlayers <=5 && numberOfPlayers>=players.size()) {
			this.numberOfPlayers = numberOfPlayers;
			unreadyPlayers();
		}
	}

	public ArrayList<User> getPlayers() {
		return players;
	}
	
	public JsonObject toJSON() {
		JsonObject json = new JsonObject();
		json.addProperty("numberOfPlayers", numberOfPlayers);
		JsonArray JsonPlayers = new JsonArray();
		for (User player : players) {
			JsonPlayers.add(player.getName());
		}
		json.add("players", JsonPlayers);
		return json;
	}
	
	private void unreadyPlayers() {
		for (User player : players) {
			player.setReady(false);
		}
	}
	
	public void sendChat(JsonObject json) {
		for (User connection : players) {
			connection.send(json);
		}
	}
	
}
