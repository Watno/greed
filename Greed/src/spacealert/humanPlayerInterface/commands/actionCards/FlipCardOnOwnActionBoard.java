package spacealert.humanPlayerInterface.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;

import java.util.UUID;

public class FlipCardOnOwnActionBoard implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;

    public FlipCardOnOwnActionBoard(UUID cardId) {
        this.cardId = cardId;
    }

    @Override
    public void execute(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player) {
        player.flipCardOnActionBoard(cardId);
    }
}
