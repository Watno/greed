package spacealert.core.gamestates;

import spacealert.core.Color;
import spacealert.core.actionCards.ActionCard;

import java.util.Collection;

public class PrivateGameState {
    final public Color ownColor;
    final public Collection<ActionCard> hand;

    public PrivateGameState(Color ownColor, Collection<ActionCard> hand) {
        this.ownColor = ownColor;
        this.hand = hand;
    }
}
