package server;

import java.time.LocalDateTime;

import org.webbitserver.WebSocketConnection;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class User {
	private WebSocketConnection connection;
	private String name = "defaultname";
	private Table table = null;
	private boolean ready = false;

	public User(WebSocketConnection connection) {
		this.connection = connection;
	}

	public synchronized JsonElement requestInput(JsonObject request) {
		send(request);
		try {
			wait();
			sendInputAcceptance();
			return (JsonElement) connection.data("input");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}

	public synchronized void resign() {
		table.removePlayer(this);
		connection.data("input", 0);
		notify();
	}

	public void send(JsonObject json) {
		connection.send(new GsonBuilder().setPrettyPrinting().create().toJson(json));
	}

	public void allowReturnToLobby() {
		JsonObject json = new JsonObject();
		json.addProperty("request", "returnToLobby");
		json.addProperty("optional", false);
		json.addProperty("reason", "Game Over");
		send(json);
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
			for (User player : table.getPlayers()) {
				starting &= player.isReady();
			}
			if (starting) {
				table.startGame();
			}
		}
	}

	private void sendInputAcceptance() {
		JsonObject json = new JsonObject();
		json.addProperty("request", "empty");
		json.addProperty("optional", false);
		json.addProperty("reason", "");
		send(json);
	}
}
