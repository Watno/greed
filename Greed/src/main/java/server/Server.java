package server;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import server.connections.WebsocketHandler;
import server.games.IGameFactory;
import server.lobbies.Chat;
import server.lobbies.Lobby;

import java.util.HashMap;
import java.util.Map;

public class Server {

    private Map<String, IGameFactory> gameFactories = new HashMap<>();

    public Server(int port) {
        WebServer webServer = WebServers.createWebServer(port);
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
