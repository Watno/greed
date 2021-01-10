package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.*;
import carnivalOfMonsters.core.logging.ILogEntry;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public abstract class StaffMember extends Card implements ICanBeInPlay {

    @JsonProperty
    private final int cost;

    protected StaffMember(String name, int cost) {
        super("StaffMember", name);
        this.cost = cost;
    }

    @Override
    public boolean checkRequirement(Player playingPlayer) {
        return true;
    }

    @Override
    public void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext) {
        playingPlayer.pay(cost, loggingContext);

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
