package carnivalOfMonsters.core.monsters;

import carnivalOfMonsters.core.*;
import carnivalOfMonsters.core.logging.ILogEntry;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Optional;

public abstract class Monster extends Card implements ICanBeInPlay, ICanBeScored {

    @JsonProperty
    protected final LandType landType;
    @JsonProperty
    protected final int level;

    @JsonProperty
    private final int dangerLevel;
    @JsonProperty
    private final int monstrousLore;
    @JsonProperty
    private final int victoryPoints;

    protected Monster(String name, LandType landType, int level, int dangerLevel, int monstrousLore, int victoryPoints) {
        super("Monster", name);
        this.landType = landType;
        this.level = level;
        this.dangerLevel = dangerLevel;
        this.monstrousLore = monstrousLore;
        this.victoryPoints = victoryPoints;
    }

    @Override
    public int getProvidedLandPoints(LandType landType) {
        return 0;
    }

    @Override
    public void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext) {
        playingPlayer.draw(game, monstrousLore);
    }

    @JsonProperty
    public LandType getLandType() {
        return landType;
    }

    public int getLevel() {
        return level;
    }

    @Override
    public int getDangerLevel() {
        return dangerLevel;
    }

    @Override
    public int score(Player player, Optional<ILogEntry> loggingContext) {
        return victoryPoints;
    }
}
