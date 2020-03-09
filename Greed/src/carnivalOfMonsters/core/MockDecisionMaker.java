package carnivalOfMonsters.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MockDecisionMaker implements IDecisionMaker {
    private Player player;

    @Override
    public Map<LandType, Integer> assignLandpoints(int requiredLandpoints) {
        Map<LandType, Integer> result = new HashMap<>();
        for (var landType : Stream.of(LandType.values()).sorted(Comparator.comparing(x -> x.equals(LandType.DREAMLANDS))).collect(Collectors.toList())) {
            var assignableLandpoints = Integer.min(requiredLandpoints, player.getAvailableLandPoints(landType));
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
    public void registerPlayer(Player player) {
        this.player = player;

    }

}
