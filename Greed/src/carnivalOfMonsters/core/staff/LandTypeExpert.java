package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.ITriggerOnPlay;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

public class LandTypeExpert extends StaffMember implements ITriggerOnPlay {

    private LandType landType;

    public LandTypeExpert(String name, LandType landType) {
        super(name, 3);
        this.landType = landType;
    }


    @Override
    public boolean triggersOn(Player player, ICanBePlayed card) {
        if (!(card instanceof Monster)) return false;
        var monster = (Monster) card;
        return monster.getLandType() == landType;
    }

    @Override
    public void trigger(Player player, ICanBePlayed card) {
        player.gainCrowns(2);
    }


}
