package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface IPlanningPhaseCommand {
    public void execute(PlanningPhase planningPhase, Player player);
}
