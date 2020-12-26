package spacealert.humanPlayerInterface.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;

import java.util.UUID;

public class RetrieveCardFromAndroidActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;
    @JsonProperty
    private UUID actionBoardId;

    public RetrieveCardFromAndroidActionBoardCommand(UUID cardId, UUID actionBoardId) {
        this.cardId = cardId;
        this.actionBoardId = actionBoardId;
    }

    @Override
    public void execute(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player) {
        planningPhase.retrieveCardFromAndroidActionBoard(player, actionBoardId, cardId);
    }
}
