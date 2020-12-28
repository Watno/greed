package spacealert.core.planningPhase.commands.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.planningPhase.PlanningPhase;
import spacealert.core.planningPhase.Player;

import java.util.UUID;

public class RetrieveCardFromOwnActionBoardCommand implements IPlanningPhaseCommand {
    @JsonProperty
    private UUID cardId;

    public RetrieveCardFromOwnActionBoardCommand(UUID cardId) {
        this.cardId = cardId;
    }

    @Override
    public void execute(PlanningPhase planningPhase, Player player) {
        player.retrieveCardFromActionBoard(cardId);
    }
}
