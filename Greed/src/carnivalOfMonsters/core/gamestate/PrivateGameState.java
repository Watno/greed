package carnivalOfMonsters.core.gamestate;

import carnivalOfMonsters.core.ICard;

import java.util.Collection;

public class PrivateGameState {
    public String name;
    public Collection<ICard> keptCards;

    public PrivateGameState(String name, Collection<ICard> keptCards) {
        this.name = name;
        this.keptCards = keptCards;
    }
}
