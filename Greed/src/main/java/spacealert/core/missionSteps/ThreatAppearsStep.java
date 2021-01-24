package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.threats.templates.Threat;

import java.util.ArrayList;
import java.util.Collection;

public class ThreatAppearsStep implements IMissionStep {
    private final ArrayList<Threat> threats;

    protected ThreatAppearsStep(Collection<Threat> threats) {
        super();
        this.threats = new ArrayList<>(threats);
    }

    @Override
    public GameLost execute(BoardState boardState) {
        return boardState.spawnThreats(threats);
    }

}
