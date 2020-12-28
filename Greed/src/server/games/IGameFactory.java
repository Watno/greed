package server.games;

import java.util.ArrayList;

public interface IGameFactory {
    Runnable createGame(ArrayList<IUserFromGamePerspective> connections, int numberOfPlayers);
}
