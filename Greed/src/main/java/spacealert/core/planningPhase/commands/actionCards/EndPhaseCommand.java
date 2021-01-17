package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Phase;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

public class EndPhaseCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private final Phase phase;

    @JsonCreator
    public EndPhaseCommand(Phase phase) {this.phase = phase;}

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        planningPhase.endPhaseFor(player, phase);
    }
}
