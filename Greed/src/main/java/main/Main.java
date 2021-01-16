package main;

import server.ServerBuilder;

public class Main {

    public static void main(String[] args) {

        ServerBuilder serverBuilder = new ServerBuilder(Integer.parseInt(args[0]));
        serverBuilder.RegisterGame("greed", new greed.meta.GameFactory());
        serverBuilder.RegisterGame("carnival", new carnivalOfMonsters.meta.GameFactory());
        serverBuilder.RegisterGame("spacealert", new spacealert.externalPlayerInterface.GameFactory());

        serverBuilder.build();

    }

}
