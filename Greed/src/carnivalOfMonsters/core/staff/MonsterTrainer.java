package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.ITriggerOnPlay;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

public class MonsterTrainer extends StaffMember implements ITriggerOnPlay {

    public MonsterTrainer(String name) {
        super(name, 2);
    }

    @Override
    public boolean triggersOn(Player player, ICanBePlayed card) {
        if (!(card instanceof Monster)) return false;
        var monster = (Monster) card;
        return monster.getDangerLevel() > 0;
    }

    @Override
    public void trigger(Player player, ICanBePlayed card) {
        player.gainCrowns(((Monster) card).getDangerLevel());
    }

}
