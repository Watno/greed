package server;

import io.undertow.Handlers;
import io.undertow.Undertow;
import io.undertow.server.handlers.PathHandler;
import server.connections.WebsocketHandler;
import server.games.IGameFactory;
import server.lobbies.Chat;
import server.lobbies.Lobby;

public class ServerBuilder {

    private final Undertow.Builder builder;
    private final PathHandler pathHandler = Handlers.path();

    public ServerBuilder(int port) {
        builder = Undertow.builder()
                .addHttpListener(port, "0.0.0.0")
                .setHandler(pathHandler);
    }

    public void RegisterGame(String gameName, IGameFactory factory) {
        Lobby lobby = new Lobby(factory);
        Chat chat = new Chat(lobby);
        WebsocketHandler handler = new WebsocketHandler(lobby, chat);
        pathHandler.addPrefixPath(gameName, Handlers.websocket(handler));
    }

    public void build() {
        builder.build().start();
    }
}
