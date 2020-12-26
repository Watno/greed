package spacealert.humanPlayerInterface.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.IPlanningPhaseExposedToDecisionMaker;
import spacealert.core.planningPhase.IPlayerExposedToDecisionMaker;

import java.util.UUID;

public class PlaceCardOnOwnActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;
    @JsonProperty
    private int position;

    public PlaceCardOnOwnActionBoardCommand(UUID cardId, int position) {
        this.cardId = cardId;
        this.position = position;
    }

    @Override
    public void execute(IPlanningPhaseExposedToDecisionMaker planningPhase, IPlayerExposedToDecisionMaker player) {
        player.placeCardOnOwnActionBoard(cardId, position);
    }
}
