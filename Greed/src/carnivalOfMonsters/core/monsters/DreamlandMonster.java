package carnivalOfMonsters.core.monsters;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

import java.util.Map;
import java.util.stream.Stream;

public abstract class DreamlandMonster extends Monster {

    private Map<LandType, Integer> assignedLandpoints;

    protected DreamlandMonster(String name, int level, int dangerLevel, int monstrousLore, int victoryPoints) {
        super(name, LandType.DREAMLANDS, level, dangerLevel, monstrousLore, victoryPoints);
    }

    @Override
    public int getConsumedLandPoints(LandType landType) {
        if (assignedLandpoints == null)
            return 0;
        return assignedLandpoints.getOrDefault(landType, 0);
    }

    @Override
    public boolean checkRequirement(Player playingPlayer) {
        return Stream.of(LandType.values()).mapToInt(playingPlayer::getAvailableLandPoints).sum() >= level;
    }

    @Override
    public void onPlay(Player playingPlayer, Game game) {
        assignedLandpoints = playingPlayer.getDecisionMaker().assignLandpoints(level);
        while (assignedLandpoints.values().stream().mapToInt(x -> x).sum() != level) {
            assignedLandpoints = playingPlayer.getDecisionMaker().assignLandpoints(level);
        }
        super.onPlay(playingPlayer, game);
    }

}
