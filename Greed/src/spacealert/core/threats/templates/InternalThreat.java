package spacealert.core.threats.templates;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.locations.ILocation;
import spacealert.core.boardElements.locations.Location;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.threats.Trajectory;

import java.util.List;
import java.util.stream.Collectors;

public abstract class InternalThreat extends Threat {
    private List<Position> spawnPositions;

    protected InternalThreat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, List<Position> spawnPositions) {
        super(speed, hitPoints, pointsForSurviving, pointsForDestroying);
        this.spawnPositions = spawnPositions;
    }

    protected InternalThreat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, Position spawnPosition) {
        this(speed, hitPoints, pointsForSurviving, pointsForDestroying, List.of(spawnPosition));
    }

    private List<ILocation> locations;
    private boolean survived = false;

    @Override
    public void spawn(Game game, int spawnTurn) {
        super.spawn(game, spawnTurn);
        locations = spawnPositions.stream().map(game::getStation).collect(Collectors.toList());
    }

    @Override
    Trajectory getTrajectory(Game game) {
        return game.getInternalTrajectory();
    }

    @Override
    protected void becomeDestroyed(Game game) {
        super.becomeDestroyed(game);
        removeFromLocations();
    }

    protected void removeFromLocations() {
        for (var location : locations) {
            location.removeInternalThreat(this);
        }
    }

    public boolean interceptsButton(Button button) {
        return false;
    }

    public void interceptButtonPress(Game game, Location location) {
        if (!survived) takeDamage(game, 1);
    }

    public boolean canBeTargetedByBattlebot() {
        return false;
    }

    public void getAttackedByBattlebot(Game game, ICrewMember crewMember) {
        takeDamage(game, 1);
    }

    public boolean returnsFire() {
        return false;
    }

    @Override
    protected void becomeSurvived(Game game) {
        super.becomeSurvived(game);
        survived = true;
    }

    public boolean isSurvived() {
        return survived;
    }
}
