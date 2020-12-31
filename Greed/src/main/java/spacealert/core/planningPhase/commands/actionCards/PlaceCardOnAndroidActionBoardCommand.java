package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Color;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class PlaceCardOnAndroidActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private final UUID cardId;
    @JsonProperty
    private final int position;
    @JsonProperty
    private final Color color;

    public PlaceCardOnAndroidActionBoardCommand(UUID cardId, int position, Color color) {
        this.cardId = cardId;
        this.position = position;
        this.color = color;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        planningPhase.placeCardOnAndroidActionBoard(player, color, cardId, position);
    }
}
