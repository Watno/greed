package spacealert.humanPlayerInterface.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;

import java.util.UUID;

public class RetrieveCardFromOwnActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;

    public RetrieveCardFromOwnActionBoardCommand(UUID cardId) {
        this.cardId = cardId;
    }

    @Override
    public void execute(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player) {
        player.retrieveCardFromActionBoard(cardId);
    }
}
