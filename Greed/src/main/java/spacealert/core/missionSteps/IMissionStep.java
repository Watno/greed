package spacealert.core.missionSteps;

import spacealert.core.BoardState;
import spacealert.core.GameLost;

public interface IMissionStep {
    GameLost execute(BoardState boardState);
}
