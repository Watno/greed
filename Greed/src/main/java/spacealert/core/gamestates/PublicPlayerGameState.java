package spacealert.core.gamestates;

import spacealert.core.Color;
import spacealert.core.actionCards.ActionBoard;

public class PublicPlayerGameState {
    final public Color color;
    final public ActionBoard actionBoard;
    final public int handsize;
    final public boolean isAllowedToPassACard;

    public PublicPlayerGameState(Color color, ActionBoard actionBoard, int handsize, boolean isAllowedToPassACard) {
        this.color = color;
        this.actionBoard = actionBoard;
        this.handsize = handsize;
        this.isAllowedToPassACard = isAllowedToPassACard;
    }

}
