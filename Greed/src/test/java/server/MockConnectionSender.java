package server;

import server.connections.AbstractConnectionSender;

public class MockConnectionSender extends AbstractConnectionSender {

    @Override
    protected void send(String message) {
        System.out.println(message);
    }
}
