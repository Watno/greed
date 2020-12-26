package spacealert.humanPlayerInterface.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;

import java.util.UUID;

public class PlaceCardOnAndroidActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;
    @JsonProperty
    private int position;
    @JsonProperty
    private UUID actionBoardId;

    public PlaceCardOnAndroidActionBoardCommand(UUID cardId, int position, UUID actionBoardId) {
        this.cardId = cardId;
        this.position = position;
        this.actionBoardId = actionBoardId;
    }

    @Override
    public void execute(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player) {
        planningPhase.placeCardOnAndroidActionBoard(player, actionBoardId, cardId, position);
    }
}
