package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class PlaceCardOnAndroidActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private final UUID cardId;
    @JsonProperty
    private final int position;
    @JsonProperty
    private final UUID actionBoardId;

    public PlaceCardOnAndroidActionBoardCommand(UUID cardId, int position, UUID actionBoardId) {
        this.cardId = cardId;
        this.position = position;
        this.actionBoardId = actionBoardId;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        planningPhase.placeCardOnAndroidActionBoard(player, actionBoardId, cardId, position);
    }
}
