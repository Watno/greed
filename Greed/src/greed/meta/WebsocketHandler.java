package greed.meta;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import greed.meta.lobby.Lobby;
import greed.meta.lobby.Table;

import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;
 
public class WebsocketHandler extends BaseWebSocketHandler {
 
	private ArrayList<GreedConnection> connections;
	private Lobby lobby;
	private Chat chat; 
	 
	public WebsocketHandler(ArrayList<GreedConnection> connections){
		super();
		this.connections=connections;
		lobby = new Lobby();
		chat = new Chat(lobby);
	}
	
	@Override
	public void onOpen(WebSocketConnection connection) {
		System.out.println(LocalDateTime.now() +" - " + "new connection");
		synchronized (connections){
			GreedConnection greedConnection = new GreedConnection(connection);
			lobby.addConnection(greedConnection);
//			connections.add(greedConnection);
//			connections.notify();
			connection.data("GreedConnection",greedConnection);
			lobby.sendJSON();
//			lobby.addConnection(greedConnection);
//			lobby.makeTable(greedConnection);
//			greedConnection.getTable().startGame();
		}
	}
	 
	@Override
	public void onClose(WebSocketConnection connection) {
		GreedConnection greedConn = (GreedConnection) connection.data("GreedConnection");
		System.out.println(LocalDateTime.now() +" - " + greedConn.getName() + " closed their connection");
		greedConn.resign();
		Table table = greedConn.getTable();
		if (table!=null) {
			table.removePlayer(greedConn);
		}
	}
	 
	@Override
	public void onMessage(WebSocketConnection connection, String message) {
		JsonParser parser = new JsonParser();
		GreedConnection greedConn = (GreedConnection) connection.data("GreedConnection");
		try {
			JsonObject parsedMessage = parser.parse(message).getAsJsonObject();
			if (parsedMessage.has("chat")) {
				chat.handleMessage(parsedMessage, greedConn);
			}
			if (parsedMessage.has("greedcommand")) {
				connection.data("input", parsedMessage.get("greedcommand").getAsInt());
				synchronized(greedConn) {
					greedConn.notify();
				}
			}
			else {
				if (parsedMessage.has("namechange")) {
					greedConn.setName(parsedMessage.get("namechange").getAsString());
				}
				if (parsedMessage.has("ready")) {
					greedConn.setReady(parsedMessage.get("ready").getAsBoolean());
				}
				if (parsedMessage.has("newTable")) {
					lobby.makeTable(greedConn);
				}
				if (parsedMessage.has("changePlayers")) {
					greedConn.getTable().setNumberOfPlayers(parsedMessage.get("changePlayers").getAsInt());
				}
				if (parsedMessage.has("joinTable")) {
					lobby.getTables().get(parsedMessage.get("joinTable").getAsInt()).addPlayer(greedConn);
				}
				if (parsedMessage.has("leaveTable")) {
					greedConn.getTable().removePlayer(greedConn);
				}
				if (parsedMessage.has("joinLobby")) {
					greedConn.resign();
					greedConn.setTable(null);
					lobby.addConnection(greedConn);
				}
				lobby.sendJSON();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}