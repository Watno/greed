package spacealert.core.gamestates;

import spacealert.core.Color;
import spacealert.core.actionCards.ActionBoard;

public class PublicAndroidGameState {
    final public Color color;
    final public ActionBoard actionBoard;

    public PublicAndroidGameState(Color color, ActionBoard actionBoard) {
        this.color = color;
        this.actionBoard = actionBoard;
    }

}
