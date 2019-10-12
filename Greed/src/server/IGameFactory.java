package server;

import java.util.ArrayList;

public interface IGameFactory {
	public Runnable createGame(ArrayList<IUserFromGamePerspective> connections, int numberOfPlayers);
}
