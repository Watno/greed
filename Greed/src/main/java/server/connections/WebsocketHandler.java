package server.connections;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import io.undertow.websockets.WebSocketConnectionCallback;
import io.undertow.websockets.core.AbstractReceiveListener;
import io.undertow.websockets.core.BufferedBinaryMessage;
import io.undertow.websockets.core.BufferedTextMessage;
import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.spi.WebSocketHttpExchange;
import server.lobbies.Chat;
import server.lobbies.Lobby;
import server.lobbies.LobbyUser;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class WebsocketHandler implements WebSocketConnectionCallback {

    private final ArrayList<LobbyUser> connections = new ArrayList<>();
    private final Lobby lobby;
    private final Chat chat;


    public WebsocketHandler(Lobby lobby, Chat chat) {
        super();
        this.lobby = lobby;
        this.chat = chat;
    }

    @Override
    public void onConnect(WebSocketHttpExchange exchange, WebSocketChannel channel) {
        System.out.println(LocalDateTime.now() + " - " + "new connection");
        synchronized (connections) {
            LobbyUser lobbyUser = new LobbyUser(new WebsocketConnectionSender(channel));
            lobby.addConnection(lobbyUser);
            channel.getReceiveSetter().set(new ReceiveListener(lobbyUser, lobby, chat));
            channel.resumeReceives();
            lobby.sendJSON();
        }
    }

    static class ReceiveListener extends AbstractReceiveListener {
        private final LobbyUser lobbyUser;
        private final Lobby lobby;
        private final Chat chat;

        ReceiveListener(LobbyUser lobbyUser, Lobby lobby, Chat chat) {
            this.lobbyUser = lobbyUser;
            this.lobby = lobby;
            this.chat = chat;
        }


        @Override
        protected void onFullTextMessage(WebSocketChannel channel, BufferedTextMessage message) throws IOException {
            try {
                JsonObject parsedMessage = JsonParser.parseString(message.getData()).getAsJsonObject();
                if (parsedMessage.has("chat")) {
                    chat.handleMessage(parsedMessage, lobbyUser);
                }
                if (parsedMessage.has("gamecommand")) {
                    lobbyUser.forwardToGame(parsedMessage.get("gamecommand"));
                } else {
                    if (parsedMessage.has("namechange")) {
                        lobbyUser.setName(parsedMessage.get("namechange").getAsString());
                    }
                    if (parsedMessage.has("ready")) {
                        lobbyUser.setReady(parsedMessage.get("ready").getAsBoolean());
                    }
                    if (parsedMessage.has("newTable")) {
                        lobby.makeTable(lobbyUser);
                    }
                    if (parsedMessage.has("changePlayers")) {
                        lobbyUser.getTable().setNumberOfPlayers(parsedMessage.get("changePlayers").getAsInt());
                    }
                    if (parsedMessage.has("joinTable")) {
                        lobby.getTables().get(parsedMessage.get("joinTable").getAsInt()).addPlayer(lobbyUser);
                    }
                    if (parsedMessage.has("leaveTable")) {
                        lobbyUser.getTable().removePlayer(lobbyUser);
                    }
                    if (parsedMessage.has("joinLobby")) {
                        lobbyUser.resign();
                        lobby.addConnection(lobbyUser);
                    }
                    lobby.sendJSON();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            super.onFullTextMessage(channel, message);
        }

        @Override
        protected void onFullCloseMessage(WebSocketChannel channel, BufferedBinaryMessage message) throws IOException {
            System.out.println(LocalDateTime.now() + " - " + lobbyUser.getName() + " closed their connection");
            lobbyUser.resign();
            super.onFullCloseMessage(channel, message);
        }


    }
}