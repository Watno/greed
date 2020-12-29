package spacealert.core.missionSteps;

import spacealert.core.Game;
import spacealert.core.GameLost;

public interface IMissionStep {
    GameLost execute(Game game);
}
