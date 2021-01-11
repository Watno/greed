package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Color;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class RetrieveCardFromAndroidActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;
    @JsonProperty
    private Color color;

    @JsonCreator
    public RetrieveCardFromAndroidActionBoardCommand(UUID cardId, Color color) {
        this.cardId = cardId;
        this.color = color;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        planningPhase.retrieveCardFromAndroidActionBoard(player, color, cardId);
    }
}
