package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class RetrieveCardFromAndroidActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;
    @JsonProperty
    private UUID actionBoardId;

    @JsonCreator
    public RetrieveCardFromAndroidActionBoardCommand(UUID cardId, UUID actionBoardId) {
        this.cardId = cardId;
        this.actionBoardId = actionBoardId;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        planningPhase.retrieveCardFromAndroidActionBoard(player, actionBoardId, cardId);
    }
}
