package carnivalOfMonsters.core.monsters;

import carnivalOfMonsters.core.*;
import com.google.gson.annotations.Expose;

public abstract class Monster extends Card implements ICanBeInPlay, ICanBeScored {

    @Expose
    protected LandType landType;
    @Expose
    protected int level;

    @Expose
    private int dangerLevel;
    @Expose
    private int monstrousLore;

    private int victoryPoints;

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
    public void onPlay(Player playingPlayer, Game game) {
        playingPlayer.draw(game, monstrousLore);
    }

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
    public int score(Player player) {
        return victoryPoints;
    }
}
