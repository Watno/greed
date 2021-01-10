package server;

import server.connections.AbstractConnectionSender;

public class MockConnectionSender extends AbstractConnectionSender {

    @Override
    protected void send(String string) {
        System.out.println(string);
    }
}
