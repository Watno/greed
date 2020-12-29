package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class FlipCardOnOwnActionBoard implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;

    @JsonCreator
    public FlipCardOnOwnActionBoard(UUID cardId) {
        this.cardId = cardId;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        player.flipCardOnActionBoard(cardId);
    }
}
