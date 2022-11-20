package spacealert.core.gamestates;

import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.Trajectory;
import spacealert.core.threats.templates.Threat;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class PublicGameState {
    final public Collection<PublicPlayerGameState> playerGameStates;
    final public Collection<PublicAndroidGameState> androids;
    final public List<List<Threat>> threatsBySpawn;
    final public Map<Zone, Trajectory> trajectories;
    final public Trajectory internalTrajectory;

    public PublicGameState(Collection<PublicPlayerGameState> playerGameStates, Collection<PublicAndroidGameState> androids, List<List<Threat>> threatsBySpawn, Map<Zone, Trajectory> trajectories, Trajectory internalTrajectory) {
        this.playerGameStates = playerGameStates;
        this.androids = androids;
        this.threatsBySpawn = threatsBySpawn;
        this.trajectories = trajectories;
        this.internalTrajectory = internalTrajectory;
    }
}
