package server;

import java.util.ArrayList;
import java.util.Map;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class Lobby {
	private ArrayList<Table> tables = new ArrayList<Table>();
	private ArrayList<User> connections = new ArrayList<User>();
	private Map<String, IGameFactory> gameFactories;
	
	public Lobby(Map<String, IGameFactory> gameFactories) {
		this.gameFactories = gameFactories;
	}

	public void makeTable(User player) {
		tables.add(new Table(gameFactories.get("Greed"), player, this));
	}
	
	public void removeTable(Table table) {
		tables.remove(table);
		for (User connection : table.getPlayers()) {
			removeConnection(connection);
		}
	}
	
	public void removeConnection(User connection) {
		connections.remove(connection);
	}
	public void addConnection(User connection) {
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
		for (User connection : connections) {
			if (connection.getTable()!=null) {
				json.addProperty("tablenumber", tables.indexOf(connection.getTable()));
			}
			else {
				json.remove("tablenumber");
			}
			connection.send(json);
		}
	}
	
	public void sendChat(JsonObject chat) {
		for (User connection : connections) {
			connection.send(chat);
		}
	}

	public ArrayList<Table> getTables() {
		return tables;
	}
}
