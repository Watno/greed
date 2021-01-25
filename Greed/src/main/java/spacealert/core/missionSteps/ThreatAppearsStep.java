package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.threats.templates.Threat;

import java.util.ArrayList;
import java.util.Collection;

public class ThreatAppearsStep extends MissionStep {
    private final ArrayList<Threat> threats;

    protected ThreatAppearsStep(Collection<Threat> threats) {
        super();
        this.threats = new ArrayList<>(threats);
    }

    @Override
    public GameLost doExecutionRules(BoardState boardState) {
        return boardState.spawnThreats(threats);
    }

}
