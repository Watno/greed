package spacealert.core.planningPhase;

import java.util.UUID;

public interface IPlanningPhaseExposedToDecisionMaker {
    void flipCardOnAndroidActionBoard(UUID cardId);

    void placeCardOnAndroidActionBoard(IPlayerExposedToDecisionMaker player, UUID actionBoardId, UUID cardId, int position);

    void retrieveCardFromAndroidActionBoard(IPlayerExposedToDecisionMaker player, UUID actionBoardId, UUID cardId);
}
