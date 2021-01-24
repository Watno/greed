package spacealert.externalPlayerInterface;

import server.games.IGameFactory;
import server.games.IUserFromGamePerspective;
import spacealert.core.Game;
import spacealert.core.IDecisionMaker;
import spacealert.core.planningPhase.eventSequences.premades.Mission1;
import spacealert.core.planningPhase.eventSequences.threatProviders.RandomThreatProvider;
import spacealert.core.planningPhase.eventSequences.threatProviders.ThreatLevel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class GameFactory implements IGameFactory {
    @Override
    public Runnable createGame(ArrayList<IUserFromGamePerspective> connections, int numberOfPlayers) {
        var externalDecisionMakers = connections.stream().map(ExternalDecisionMaker::new).collect(Collectors.<IDecisionMaker>toList());
        return new Game(externalDecisionMakers, numberOfPlayers, new Mission1(new RandomThreatProvider(Arrays.asList(ThreatLevel.WHITE), Arrays.asList(ThreatLevel.WHITE))));
    }
}
