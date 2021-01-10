package main;

import server.Server;

public class Main {

    public static void main(String[] args) {

        Server server = new Server(Integer.parseInt(args[0]));
        server.RegisterGame("greed", new greed.meta.GameFactory());
        server.RegisterGame("carnival", new carnivalOfMonsters.meta.GameFactory());

    }

}
