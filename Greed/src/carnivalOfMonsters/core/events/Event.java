package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Card;
import carnivalOfMonsters.core.ICanBePlayed;
import carnivalOfMonsters.core.Player;

public abstract class Event extends Card implements ICanBePlayed {

    protected Event(String name) {
        super("Event", name);
    }

    @Override
    public boolean checkRequirement(Player playingPlayer) {
        return true;
    }

}
