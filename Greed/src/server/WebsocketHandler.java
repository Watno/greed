package server;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.webbitserver.BaseWebSocketHandler;
import org.webbitserver.WebSocketConnection;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WebsocketHandler extends BaseWebSocketHandler {

    private final ArrayList<User> connections = new ArrayList<>();
    private Lobby lobby;
    private Chat chat;


    public WebsocketHandler(Lobby lobby, Chat chat) {
        super();
        this.lobby = lobby;
        this.chat = chat;
    }


    @Override
    public void onOpen(WebSocketConnection connection) {
        System.out.println(LocalDateTime.now() + " - " + "new connection");
        synchronized (connections) {
            User greedConnection = new User(connection);
            lobby.addConnection(greedConnection);

            connection.data("GreedConnection", greedConnection);
            lobby.sendJSON();
        }
    }

    @Override
    public void onClose(WebSocketConnection connection) {
        User greedConn = (User) connection.data("GreedConnection");
        System.out.println(LocalDateTime.now() + " - " + greedConn.getName() + " closed their connection");
        greedConn.resign();
    }

    @Override
    public void onMessage(WebSocketConnection connection, String message) {
        JsonParser parser = new JsonParser();
        User greedConn = (User) connection.data("GreedConnection");
        try {
            JsonObject parsedMessage = parser.parse(message).getAsJsonObject();
            if (parsedMessage.has("chat")) {
                chat.handleMessage(parsedMessage, greedConn);
            }
            if (parsedMessage.has("greedcommand")) {
                greedConn.forwardToGame(parsedMessage.get("greedcommand"));
            } else {
                if (parsedMessage.has("namechange")) {
                    greedConn.setName(parsedMessage.get("namechange").getAsString());
                }
                if (parsedMessage.has("ready")) {
                    greedConn.setReady(parsedMessage.get("ready").getAsBoolean());
                }
                if (parsedMessage.has("newTable")) {
                    lobby.makeTable(greedConn, parsedMessage.get("newTable").getAsString());
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
                    lobby.addConnection(greedConn);
                }
                lobby.sendJSON();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}