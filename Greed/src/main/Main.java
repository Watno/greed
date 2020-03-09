package main;

import greed.meta.GameFactory;
import server.Server;

public class Main {

    public static void main(String[] args) {

        Server server = new Server();
        server.RegisterGameFactory("Greed", new GameFactory());

    }

}
