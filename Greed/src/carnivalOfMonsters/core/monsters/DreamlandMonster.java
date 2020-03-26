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
        Map<LandType, Integer> assignment = null;
        do {
            assignment = playingPlayer.getDecisionMaker().assignLandpoints(level);
        } while (!assignedLandpointsAreValid(assignment, playingPlayer));
        assignedLandpoints = assignment;
        super.onPlay(playingPlayer, game);
    }

    private boolean assignedLandpointsAreValid(Map<LandType, Integer> assignment, Player player) {
        if (assignment == null) return false;
        if (assignment.values().stream().mapToInt(x -> x).sum() != level) return false;
        for (var landType : LandType.values()) {
            if (assignment.get(landType) > player.getAvailableLandPoints(landType)) return false;
        }
        return true;
    }

}
