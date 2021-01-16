package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class FlipCardOnOwnActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private final UUID cardId;

    @JsonCreator
    public FlipCardOnOwnActionBoardCommand(UUID cardId) {
        this.cardId = cardId;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        player.flipCardOnActionBoard(cardId);
    }
}
