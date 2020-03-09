package carnivalOfMonsters.core.monsters;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public abstract class NormalMonster extends Monster {

    protected NormalMonster(String name, LandType landType, int level, int dangerLevel, int monstrousLore, int victoryPoints) {
        super(name, landType, level, dangerLevel, monstrousLore, victoryPoints);
    }

    @Override
    public int getConsumedLandPoints(LandType landType) {
        if (landType == this.landType) {
            return level;
        } else return 0;
    }

    @Override
    public boolean checkRequirement(Player playingPlayer) {
        return playingPlayer.getAvailableLandPoints(landType) >= level;
    }

}
