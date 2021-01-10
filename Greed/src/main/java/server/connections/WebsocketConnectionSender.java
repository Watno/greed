package server.connections;

import io.undertow.websockets.core.WebSocketChannel;
import io.undertow.websockets.core.WebSockets;

public class WebsocketConnectionSender extends AbstractConnectionSender {
    private final WebSocketChannel connection;


    public WebsocketConnectionSender(WebSocketChannel connection) {
        this.connection = connection;
    }

    @Override
    protected void send(String message) {
        WebSockets.sendText(message, connection, null);
    }
}
