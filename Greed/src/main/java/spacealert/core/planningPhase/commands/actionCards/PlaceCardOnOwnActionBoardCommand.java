package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class PlaceCardOnOwnActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;
    @JsonProperty
    private int position;

    @JsonCreator
    public PlaceCardOnOwnActionBoardCommand(UUID cardId, int position) {
        this.cardId = cardId;
        this.position = position;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        player.placeCardOnOwnActionBoard(cardId, position);
    }
}
