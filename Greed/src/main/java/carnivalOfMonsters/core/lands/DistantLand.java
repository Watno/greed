package carnivalOfMonsters.core.lands;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class DistantLand extends Land {

    @JsonProperty
    private final int requiredLandpoints;

    protected DistantLand(String name, LandType landType, int landpoints, int requiredLandpoints) {
        super(name, landType, landpoints);
        this.requiredLandpoints = requiredLandpoints;
    }

    @Override
    public boolean checkRequirement(Player playingPlayer) {
        return playingPlayer.getTotalLandPoints(landType) >= requiredLandpoints;
    }

}
