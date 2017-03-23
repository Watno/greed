package greed.meta;

import greed.game.GreedGame;

public class GreedThread implements Runnable {
	GreedConnection connection;
	
	public GreedThread(GreedConnection connection) {
		this.connection = connection;
	}
	@Override
	public void run() {
    	GreedGame theGame = new GreedGame(3);
    	theGame.addRealPlayer(connection);
    	theGame.startGame();
	}

}
