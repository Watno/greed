package server;

import java.util.HashMap;
import java.util.Map;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;

public class Server {

	private Map<String, IGameFactory> gameFactories = new HashMap<String, IGameFactory>();
	
	public Server() {
	    WebServer webServer = WebServers.createWebServer(8080);
	    Lobby lobby = new Lobby(gameFactories);
	    Chat chat = new Chat(lobby);
	    WebsocketHandler handler = new WebsocketHandler(lobby, chat);
	    webServer.add("/greed", handler);
	    webServer.start();
	}
	
	public void RegisterGameFactory(String gameName, IGameFactory factory) {
		gameFactories.put(gameName, factory);
	}
}
