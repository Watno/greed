package carnivalOfMonsters.core.gamestate;

import carnivalOfMonsters.core.ICard;

import java.util.ArrayList;
import java.util.Collection;

public class PrivateGameState {
    public Collection<ICard> keptCards;

    public PrivateGameState(ArrayList<ICard> keptCards) {
        this.keptCards = keptCards;
    }
}
