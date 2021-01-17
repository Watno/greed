package spacealert.core.planningPhase;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Color;
import spacealert.core.Phase;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.ActionCard;

import java.util.Optional;
import java.util.UUID;

public class Android {
    @JsonProperty
    private final Color color;
    @JsonProperty
    private final ActionBoard actionBoard;

    public Android(Color color) {
        this.color = color;
        this.actionBoard = new ActionBoard();
    }

    public void flipCardOnActionBoard(UUID cardId, Phase phase) {
        actionBoard.flipCardById(cardId, phase);
    }

    public boolean tryPlaceCardOnActionBoard(ActionCard card, int position, Optional<Phase> phase) {
        return actionBoard.tryPlace(position, card, phase);
    }

    public Optional<ActionCard> retrieveCardFromActionBoard(UUID cardId, Phase phase) {
        return actionBoard.returnCardById(cardId, phase);
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }
}
