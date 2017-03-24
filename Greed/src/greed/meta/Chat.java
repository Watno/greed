package greed.meta;

import greed.meta.lobby.Lobby;
import greed.meta.lobby.Table;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class Chat {
	private Lobby lobby;
	
	public Chat(Lobby lobby) {
		this.lobby=lobby;
	}
	
	public void handleMessage(JsonObject receivedMessage, GreedConnection connection) {
		JsonObject json = makeJSON(receivedMessage.get("chat").getAsString(), connection);
		if (receivedMessage.get("location").getAsString().equals("lobby")) {
			sendToLobby(json);
		}
		if (receivedMessage.get("location").getAsString().equals("table")) {
			sendToTable(json, connection.getTable());
		}
	}
	
	private JsonObject makeJSON(String message, GreedConnection connection){
		JsonObject json = new JsonObject();
		json.addProperty("type", "chat");
		json.addProperty("message", message);
		json.addProperty("sender", connection.getName());
		return json;
	}
	
	private void sendToLobby(JsonObject json) {
		json.addProperty("location", "lobby");
		lobby.sendChat(new GsonBuilder().setPrettyPrinting().create().toJson(json));
	}
	
	private void sendToTable(JsonObject json, Table table) {
		json.addProperty("location", "table");
		table.sendChat(new GsonBuilder().setPrettyPrinting().create().toJson(json));
	}
	
}
