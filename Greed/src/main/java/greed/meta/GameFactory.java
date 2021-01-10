package greed.meta;

import server.games.IGameFactory;
import server.games.IUserFromGamePerspective;

import java.util.ArrayList;

public class GameFactory implements IGameFactory {

	@Override
	public Runnable createGame(ArrayList<IUserFromGamePerspective> connections, int numberOfPlayers) {
		return new GreedThreadFromLobby(connections, numberOfPlayers);
	}

}
