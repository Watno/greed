package spacealert.humanPlayerInterface.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;

import java.util.UUID;

public class FlipCardOnAndroidActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;

    public FlipCardOnAndroidActionBoardCommand(UUID cardId) {
        this.cardId = cardId;
    }

    @Override
    public void execute(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player) {
        planningPhase.flipCardOnAndroidActionBoard(cardId);
    }
}
