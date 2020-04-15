package carnivalOfMonsters.core;

import carnivalOfMonsters.core.gamestate.GameStateWithPrivateInfo;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;

public interface IDecisionMaker {

    Map<LandType, Integer> assignLandpoints(int requiredLandpoints);

    LandType chooseLandTypeForExplorer();

    ICard pickCardToDraft(Collection<ICard> draftstack);

    PlayOrKeep choosePlayOrKeep(ICanBePlayed card);

    Optional<ICanBePlayed> chooseKeptCardToPlay(Collection<ICanBePlayed> keptCards);

    void sendGameState(GameStateWithPrivateInfo gameState);
}
