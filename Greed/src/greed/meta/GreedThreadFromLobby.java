package greed.meta;

import java.time.LocalDateTime;
import java.util.ArrayList;

import greed.game.GreedGame;

public class GreedThreadFromLobby implements Runnable {
	private ArrayList<GreedConnection> connections;
	private int numberOfPlayers;
	
	public GreedThreadFromLobby(ArrayList<GreedConnection> connections, int numberOfPlayers) {
		this.connections = connections;
		this.numberOfPlayers = numberOfPlayers;
	}
	@Override
	public void run() {
		GreedGame theGame = new GreedGame(numberOfPlayers);
		for (GreedConnection connection : connections) {
			System.out.println(LocalDateTime.now() +" - " + connection.getName());
			theGame.addRealPlayer(connection);
		}
		theGame.startGame();
		System.out.println(LocalDateTime.now() +" - " + "Game finished, players: ");
		for (GreedConnection thePlayer: connections) {
			System.out.println(LocalDateTime.now() +" - " + thePlayer.getName());
			thePlayer.setDecider(null);
			thePlayer.setTable(null);
			thePlayer.allowReturnToLobby();
		}
	}

}
