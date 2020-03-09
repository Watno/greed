package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.ITriggerOnPlay;
import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

public class LandTypeExpert extends StaffMember implements ITriggerOnPlay {

    private LandType landType;

    protected LandTypeExpert(LandType landType) {
        super(3);
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
