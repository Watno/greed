package spacealert.core;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.planningPhase.Android;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;
import spacealert.core.planningPhase.eventSequences.EventSequence;
import spacealert.core.threats.Trajectory;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Game implements Runnable {
    private final Map<IDecisionMaker, Player> players;
    private final List<Android> androids;
    private final EventSequence eventSequence;

    public Game(Collection<IDecisionMaker> decisionMakers, int numberOfPlayers, EventSequence eventSequence) {
        this.eventSequence = eventSequence;
        var availableColors = new ArrayDeque<>(Arrays.asList(Color.values()));
        players = decisionMakers.stream().collect(Collectors.toMap(x -> x, x -> new Player(availableColors.remove())));
        androids = Stream.generate(() -> new Android(availableColors.remove()))
                .limit(numberOfPlayers - decisionMakers.size())
                .collect(Collectors.toList());
    }

    @Override
    public void run() {

        var allTrajectories = Trajectory.all();
        var trajectories = Map.of(
                Zone.RED, allTrajectories.remove(0),
                Zone.WHITE, allTrajectories.remove(0),
                Zone.BLUE, allTrajectories.remove(0)
        );

        var internalTrajectory = allTrajectories.remove(0);

        new PlanningPhase(players, androids, trajectories, internalTrajectory).run(eventSequence);
        var boardState = new BoardState(Stream.concat(players.values().stream(), androids.stream()).collect(Collectors.toList()), trajectories, internalTrajectory);
        eventSequence.toMissionStepSequence().execute(boardState);
    }
}
