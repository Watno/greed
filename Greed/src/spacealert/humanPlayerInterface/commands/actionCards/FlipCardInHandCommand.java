package spacealert.humanPlayerInterface.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;

import java.util.UUID;

public class FlipCardInHandCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;

    public FlipCardInHandCommand(UUID cardId) {
        this.cardId = cardId;
    }

    @Override
    public void execute(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player) {
        player.flipCardInHand(cardId);
    }
}
