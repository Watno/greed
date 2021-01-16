package spacealert.externalPlayerInterface;

import server.games.IGameFactory;
import server.games.IUserFromGamePerspective;
import spacealert.core.IDecisionMaker;
import spacealert.core.planningPhase.PlanningPhase;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class GameFactory implements IGameFactory {
    @Override
    public Runnable createGame(ArrayList<IUserFromGamePerspective> connections, int numberOfPlayers) {
        var externalDecisionMakers = connections.stream().map(ExternalDecisionMaker::new).collect(Collectors.<IDecisionMaker>toList());
        return new PlanningPhase(externalDecisionMakers, numberOfPlayers);
    }
}
