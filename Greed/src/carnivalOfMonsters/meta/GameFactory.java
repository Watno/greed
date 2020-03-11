package carnivalOfMonsters.meta;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.MockDecisionMaker;
import carnivalOfMonsters.core.Player;
import server.IGameFactory;
import server.IUserFromGamePerspective;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameFactory implements IGameFactory {
    @Override
    public Runnable createGame(ArrayList<IUserFromGamePerspective> connections, int numberOfPlayers) {
        var players = connections.stream().map(x -> new Player(x.getName(), new RealDecisionMaker(x))).collect(Collectors.toList());
        while (players.size() < numberOfPlayers) {
            String name = "Player " + numberOfPlayers;
            players.add(new Player(name, new MockDecisionMaker(name)));
        }
        return new Game(players);
    }
}
