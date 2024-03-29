package carnivalOfMonsters.core.lands;

import carnivalOfMonsters.core.*;
import carnivalOfMonsters.core.logging.ILogEntry;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public abstract class Land extends Card implements ICanBeInPlay {
    @JsonProperty
    protected final LandType landType;
    @JsonProperty
    private final int landpoints;

    protected Land(String name, LandType landType, int landpoints) {
        super("Land", name);
        this.landType = landType;
        this.landpoints = landpoints;
    }

    @Override
    public int getConsumedLandPoints(LandType landType) {
        return 0;
    }

    @Override
    public int getProvidedLandPoints(LandType landType) {
        if (landType == this.landType) {
            return landpoints;
        } else return 0;
    }

    @Override
    public void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext) {
    }

    @Override
    public int getDangerLevel() {
        return 0;
    }
}
