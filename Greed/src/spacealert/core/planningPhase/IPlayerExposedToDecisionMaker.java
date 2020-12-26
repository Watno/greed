package spacealert.core.planningPhase;

import java.util.UUID;

public interface IPlayerExposedToDecisionMaker {
    void flipCardInHand(UUID cardId);

    void flipCardOnActionBoard(UUID cardId);

    void placeCardOnOwnActionBoard(UUID cardId, int position);

    void retrieveCardFromActionBoard(UUID cardId);
}
