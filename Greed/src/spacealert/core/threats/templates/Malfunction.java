package spacealert.core.threats.templates;

import spacealert.core.Button;
import spacealert.core.boardElements.positions.Position;

import java.util.List;

public abstract class Malfunction extends InternalThreat {
    private Button affectedButton;

    protected Malfunction(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, List<Position> spawnPositions, Button affectedButton) {
        super(speed, hitPoints, pointsForSurviving, pointsForDestroying, spawnPositions);
        this.affectedButton = affectedButton;
    }

    protected Malfunction(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, Position spawnPosition, Button affectedButton) {
        this(speed, hitPoints, pointsForSurviving, pointsForDestroying, List.of(spawnPosition), affectedButton);
    }

    public boolean interceptsButton(Button button) {
        return affectedButton == button;
    }


}
