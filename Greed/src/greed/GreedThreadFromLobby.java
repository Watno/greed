package greed;

import java.util.ArrayList;

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
			theGame.addRealPlayer(connection);
		}
		theGame.startGame();
		System.out.println("Game finished");
		for (GreedConnection thePlayer: connections) {
			thePlayer.setDecider(null);
			thePlayer.setTable(null);
			thePlayer.allowReturnToLobby();
		}
	}

}
