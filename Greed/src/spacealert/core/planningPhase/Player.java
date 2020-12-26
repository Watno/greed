package spacealert.core.planningPhase;

import spacealert.core.actionCards.ActionBoard;
import spacealert.core.actionCards.ActionCard;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

public class Player implements IPlayerExposedToDecisionMaker {
    private Collection<ActionCard> hand;
    private ActionBoard actionBoard;

    @Override
    public void flipCardInHand(UUID cardId) {
        findCardInHand(cardId).ifPresent(ActionCard::flip);
    }

    @Override
    public void flipCardOnActionBoard(UUID cardId) {
        actionBoard.flipCardById(cardId);
    }

    @Override
    public void placeCardOnOwnActionBoard(UUID cardId, int position) {
        placeCardOnActionBoard(actionBoard, cardId, position);
    }

    public void placeCardOnActionBoard(ActionBoard actionBoard, UUID cardId, int position) {
        findCardInHand(cardId)
                .ifPresent(card -> {
                    actionBoard.place(position, card);
                    hand.remove(card);
                });
    }

    @Override
    public void retrieveCardFromActionBoard(UUID cardId) {
        retrieveCardFromActionBoard(this.actionBoard, cardId);
    }

    public void retrieveCardFromActionBoard(ActionBoard actionBoard, UUID cardId) {
        actionBoard.returnCardById(cardId)
                .ifPresent(x -> hand.add(x));
    }

    private Optional<ActionCard> findCardInHand(UUID cardId) {
        return hand.stream().filter(x -> x.id.equals(cardId)).findFirst();
    }
}
