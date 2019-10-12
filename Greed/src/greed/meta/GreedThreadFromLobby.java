package greed.meta;

import java.time.LocalDateTime;
import java.util.ArrayList;

import greed.game.GreedGame;
import server.User;

public class GreedThreadFromLobby implements Runnable {
	private ArrayList<User> connections;
	private int numberOfPlayers;
	
	public GreedThreadFromLobby(ArrayList<User> connections, int numberOfPlayers) {
		this.connections = connections;
		this.numberOfPlayers = numberOfPlayers;
	}
	@Override
	public void run() {
		GreedGame theGame = new GreedGame(numberOfPlayers);
		for (User connection : connections) {
			System.out.println(LocalDateTime.now() +" - " + connection.getName());
			theGame.addRealPlayer(connection);
		}
		theGame.startGame();
		System.out.println(LocalDateTime.now() +" - " + "Game finished, players: ");
		for (User thePlayer: connections) {
			System.out.println(LocalDateTime.now() +" - " + thePlayer.getName());
			thePlayer.allowReturnToLobby();
		}
	}

}
