package carnivalOfMonsters.core.lands;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public abstract class DistantLand extends Land {

    private int requiredLandpoints;

    protected DistantLand(LandType landType, int landpoints, int requiredLandpoints) {
        super(landType, landpoints);
        this.requiredLandpoints = requiredLandpoints;
    }

    @Override
    public boolean checkRequirement(Player playingPlayer) {
        return playingPlayer.getTotalLandPoints(landType) >= requiredLandpoints;
    }

}
