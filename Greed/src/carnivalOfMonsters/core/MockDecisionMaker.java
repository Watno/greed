package carnivalOfMonsters.core;

import carnivalOfMonsters.core.gamestate.GameStateWithPrivateInfo;
import carnivalOfMonsters.core.gamestate.PublicPlayerGameState;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockDecisionMaker implements IDecisionMaker {
    public GameStateWithPrivateInfo gameState;
    private String playerName;

    public MockDecisionMaker(String playerName) {
        this.playerName = playerName;
    }

    @Override
    public Map<LandType, Integer> assignLandpoints(int requiredLandpoints) {
        Map<LandType, Integer> result = new HashMap<>();
        for (var landType : Stream.of(LandType.values()).sorted(Comparator.comparing(x -> x.equals(LandType.DREAMLANDS))).collect(Collectors.toList())) {
            var assignableLandpoints = Integer.min(requiredLandpoints, getOwnPlayerGameState().availableLandPoints.get(landType));
            result.put(landType, assignableLandpoints);
            requiredLandpoints -= assignableLandpoints;
        }
        return result;
    }

    @Override
    public LandType chooseLandTypeForExplorer() {
        return LandType.ENCHANTEDFOREST;
    }

    @Override
    public ICard pickCardToDraft(Collection<ICard> draftstack) {
        return draftstack.iterator().next();
    }

    @Override
    public PlayOrKeep choosePlayOrKeep(ICanBePlayed card) {
        return PlayOrKeep.PLAY;
    }

    @Override
    public Optional<ICanBePlayed> chooseKeptCardToPlay(Collection<ICanBePlayed> keptCards) {
        return Optional.empty();
    }

    @Override
    public void sendGameState(GameStateWithPrivateInfo gameState) {
        this.gameState = gameState;
    }

    private PublicPlayerGameState getOwnPlayerGameState() {
        return gameState.publicGameState.playerGameStates.stream().filter(x -> x.name.equals(playerName)).findFirst().get();
    }

}
