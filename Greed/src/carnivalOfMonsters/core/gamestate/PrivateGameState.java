package carnivalOfMonsters.core.gamestate;

import carnivalOfMonsters.core.ICard;

import java.util.Collection;

public class PrivateGameState {
    final public String name;
    final public Collection<ICard> keptCards;

    public PrivateGameState(String name, Collection<ICard> keptCards) {
        this.name = name;
        this.keptCards = keptCards;
    }
}
