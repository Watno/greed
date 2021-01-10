package greed.meta;

import greed.game.GreedGame;
import server.games.IUserFromGamePerspective;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class GreedThreadFromLobby implements Runnable {
    private final ArrayList<IUserFromGamePerspective> connections;
    private final int numberOfPlayers;

    public GreedThreadFromLobby(ArrayList<IUserFromGamePerspective> connections, int numberOfPlayers) {
        this.connections = connections;
        this.numberOfPlayers = numberOfPlayers;
    }

    @Override
    public void run() {
        GreedGame theGame = new GreedGame(numberOfPlayers);
        for (IUserFromGamePerspective connection : connections) {
            System.out.println(LocalDateTime.now() + " - " + connection.getName());
			theGame.addRealPlayer(connection);
		}
		theGame.startGame();
		System.out.println(LocalDateTime.now() +" - " + "Game finished, players: ");
		for (IUserFromGamePerspective thePlayer: connections) {
			System.out.println(LocalDateTime.now() +" - " + thePlayer.getName());
			thePlayer.allowReturnToLobby();
		}
	}

}
