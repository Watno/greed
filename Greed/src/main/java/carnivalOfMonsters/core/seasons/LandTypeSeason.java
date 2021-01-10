package carnivalOfMonsters.core.seasons;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;
import com.fasterxml.jackson.annotation.JsonProperty;

public class LandTypeSeason extends Season {

    @JsonProperty
    private final LandType landType;

    public LandTypeSeason(String name, LandType landType) {
        super(name);
        this.landType = landType;
    }

    @Override
    public boolean triggersOn(Player player, ICanBePlayed card) {
        if (!(card instanceof Monster)) return false;
        var monster = (Monster) card;
        if (player.getCardsInPlay().stream().filter(x -> x instanceof Monster).map(x -> (Monster) x).anyMatch(x -> x.getLandType() == landType))
            return false;
        return monster.getLandType() == landType;
    }

    @Override
    protected int getCompareValue(Player player) {
        return (int) player.getCardsInPlay().stream()
                .filter(x -> x instanceof Monster)
                .map(x -> (Monster) x)
                .filter(x -> x.getLandType().equals(this.landType))
                .count();
    }

}
