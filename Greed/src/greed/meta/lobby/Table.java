package greed.meta.lobby;

import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import greed.meta.GreedConnection;
import greed.meta.GreedThreadFromLobby;


public class Table {
	private ArrayList<GreedConnection> players = new ArrayList<GreedConnection>();
	private int numberOfPlayers=3;
	private Lobby lobby; 
	
	public Table(GreedConnection player, Lobby lobby) {
		addPlayer(player);
		this.lobby=lobby;
	}
	
	public void startGame() {
		unreadyPlayers();
		Thread game = new Thread (new GreedThreadFromLobby(players, numberOfPlayers));
		lobby.removeTable(this);
		game.start();
	}
	
	public boolean addPlayer(GreedConnection player) {
		unreadyPlayers();
		if (players.size()<numberOfPlayers) {
			players.add(player);
			if (player.getTable()!=null) {
				player.getTable().removePlayer(player);
			}
			player.setTable(this);
			return true;
		}
		return false;
	}
	
	public void removePlayer(GreedConnection player) {
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

	public ArrayList<GreedConnection> getPlayers() {
		return players;
	}
	
	public JsonObject toJSON() {
		JsonObject json = new JsonObject();
		json.addProperty("numberOfPlayers", numberOfPlayers);
		JsonArray JsonPlayers = new JsonArray();
		for (GreedConnection player : players) {
			JsonPlayers.add(player.getName());
		}
		json.add("players", JsonPlayers);
		return json;
	}
	
	private void unreadyPlayers() {
		for (GreedConnection player : players) {
			player.setReady(false);
		}
	}
	
}
