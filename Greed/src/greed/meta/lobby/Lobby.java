package greed.meta.lobby;

import java.util.ArrayList;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import greed.meta.GreedConnection;

public class Lobby {
	private ArrayList<Table> tables = new ArrayList<Table>();
	private ArrayList<GreedConnection> connections = new ArrayList<GreedConnection>();
	
	public void makeTable(GreedConnection player) {
		tables.add(new Table(player, this));
	}
	
	public void removeTable(Table table) {
		tables.remove(table);
		for (GreedConnection connection : table.getPlayers()) {
			removeConnection(connection);
		}
	}
	
	public void removeConnection(GreedConnection connection) {
		connections.remove(connection);
	}
	public void addConnection(GreedConnection connection) {
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
		for (GreedConnection connection : connections) {
			if (connection.getTable()!=null) {
				json.addProperty("tablenumber", tables.indexOf(connection.getTable()));
			}
			else {
				json.remove("tablenumber");
			}
			connection.sendLobby(new GsonBuilder().setPrettyPrinting().create().toJson(json));
		}
	}

	public ArrayList<Table> getTables() {
		return tables;
	}
}
