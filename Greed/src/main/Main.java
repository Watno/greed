package main;

import server.Server;

public class Main {

    public static void main(String[] args) {

        Server server = new Server(Integer.parseInt(args[0]));
        server.RegisterGameFactory("Greed", new greed.meta.GameFactory());
        server.RegisterGameFactory("CarnivalOfMonsters", new carnivalOfMonsters.meta.GameFactory());

    }

}
