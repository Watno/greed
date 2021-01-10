package carnivalOfMonsters.core.lands;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public abstract class BasicLand extends Land {

    protected BasicLand(String name, LandType landType, int landpoints) {
        super(name, landType, landpoints);
    }

    @Override
    public boolean checkRequirement(Player playingPlayer) {
        return true;
    }

}
