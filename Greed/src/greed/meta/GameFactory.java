package greed.meta;

import java.util.ArrayList;

import server.IGameFactory;
import server.IUserFromGamePerspective;

public class GameFactory implements IGameFactory {

	@Override
	public Runnable createGame(ArrayList<IUserFromGamePerspective> connections, int numberOfPlayers) {
		return new GreedThreadFromLobby(connections, numberOfPlayers);
	}

}
