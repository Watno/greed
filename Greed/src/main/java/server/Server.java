package server;

import org.webbitserver.WebServer;
import org.webbitserver.WebServers;
import server.connections.WebsocketHandler;
import server.games.IGameFactory;
import server.lobbies.Chat;
import server.lobbies.Lobby;

public class Server {

    private WebServer webServer;

    public Server(int port) {
        webServer = WebServers.createWebServer(port);
        webServer.start();
    }

    public void RegisterGame(String gameName, IGameFactory factory) {
        Lobby lobby = new Lobby(factory);
        Chat chat = new Chat(lobby);
        WebsocketHandler handler = new WebsocketHandler(lobby, chat);
        webServer.add("/" + gameName, handler);
    }
}
