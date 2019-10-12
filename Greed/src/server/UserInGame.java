package server;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class UserInGame implements IUserFromGamePerspective {
	public UserInGame(User user) {
		super();
		this.user = user;
		this.name = user.getName();
	}

	private User user;
	private String name;

	private JsonElement currentInput = null;
	
	@Override
	public synchronized JsonElement requestInput(JsonObject request) {
		send(request);
		try {
			if (!hasResigned()) {
				wait();
				sendInputAcceptance();
			}
			JsonElement toReturn = (JsonElement) currentInput;
			currentInput = null;
			return toReturn;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void allowReturnToLobby() {
		JsonObject json = new JsonObject();
		json.addProperty("request", "returnToLobby");
		json.addProperty("optional", false);
		json.addProperty("reason", "Game Over");
		send(json);
	}
	
	@Override
	public String getName() {
		return name;
	}
	
	@Override
	public void send(JsonObject json) {
		if (!hasResigned()) {
			user.send(json);	
		}
	}
	
	@Override
	public Boolean hasResigned() {
		return user == null;
	}
	
	public synchronized void receiveInput(JsonElement json) {
		currentInput = json;
		notify();
	}
	
	public synchronized void resign() {
		user = null;
		name += " (resigned)";
		notify();
	}
	
	private void sendInputAcceptance() {
		JsonObject json = new JsonObject();
		json.addProperty("request", "empty");
		json.addProperty("optional", false);
		json.addProperty("reason", "");
		send(json);
	}
	

}
