package spacealert.core.planningPhase;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Color;
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

    public void flipCardOnActionBoard(UUID cardId) {
        actionBoard.flipCardById(cardId);
    }

    public void placeCardOnActionBoard(ActionCard card, int position) {
        actionBoard.place(position, card);
    }

    public Optional<ActionCard> retrieveCardFromActionBoard(UUID cardId) {
        return actionBoard.returnCardById(cardId);
    }

    public boolean hasColor(Color color) {
        return this.color == color;
    }
}
