package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Color;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class PassCardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private final UUID cardId;

    @JsonProperty
    public final Color receivingPlayerColor;

    @JsonCreator
    public PassCardCommand(UUID cardId, Color receivingPlayerColor) {
        this.cardId = cardId;
        this.receivingPlayerColor = receivingPlayerColor;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        planningPhase.getPlayerByColor(receivingPlayerColor).ifPresent(x -> player.passCardTo(x, cardId));
    }
}
