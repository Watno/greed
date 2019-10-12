package greed.meta;

import java.util.ArrayList;

import server.IGameFactory;
import server.User;

public class GameFactory implements IGameFactory {

	@Override
	public Runnable createGame(ArrayList<User> connections, int numberOfPlayers) {
		return new GreedThreadFromLobby(connections, numberOfPlayers);
	}

}
