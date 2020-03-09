package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.*;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class StaffMember extends Card implements ICanBeInPlay {

    @JsonProperty
    private int cost;

    protected StaffMember(String name, int cost) {
        super("StaffMember", name);
        this.cost = cost;
    }

    @Override
    public boolean checkRequirement(Player playingPlayer) {
        return true;
    }

    @Override
    public void onPlay(Player playingPlayer, Game game) {
        playingPlayer.pay(cost);

    }

    @Override
    public int getProvidedLandPoints(LandType landType) {
        return 0;
    }

    @Override
    public int getConsumedLandPoints(LandType landType) {
        return 0;
    }

    @Override
    public int getDangerLevel() {
        return 0;
    }

}
