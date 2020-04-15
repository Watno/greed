package carnivalOfMonsters.core.staff;

import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.ITriggerOnPlay;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.events.Event;

public class EventCoordinator extends StaffMember implements ITriggerOnPlay {

    public EventCoordinator(String name) {
        super(name, 3);
    }

    @Override
    public boolean triggersOn(Player player, ICanBePlayed card) {
        return card instanceof Event;
    }

    @Override
    public void trigger(Player player, ICanBePlayed card) {
        player.gainCrowns(1);
    }

}
