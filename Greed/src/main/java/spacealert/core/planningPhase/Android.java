package spacealert.core.planningPhase;

import spacealert.core.Color;
import spacealert.core.CrewMember;
import spacealert.core.Phase;
import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.ActionCard;

import java.util.Optional;
import java.util.UUID;

public class Android extends CrewMember {

    public Android(Color color) {
        super(new ActionBoard(), color);
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
}
