package spacealert.core.threats.templates;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.locations.ILocation;
import spacealert.core.boardElements.locations.Location;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.threats.Trajectory;

import java.util.List;
import java.util.stream.Collectors;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "type")
public abstract class InternalThreat extends Threat {
    private final List<Position> spawnPositions;

    protected InternalThreat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, List<Position> spawnPositions) {
        super(speed, hitPoints, pointsForSurviving, pointsForDestroying);
        this.spawnPositions = spawnPositions;
    }

    protected InternalThreat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, Position spawnPosition) {
        this(speed, hitPoints, pointsForSurviving, pointsForDestroying, List.of(spawnPosition));
    }

    protected List<ILocation> locations;
    private boolean survived = false;

    @Override
    public GameLost spawn(Game game, int spawnTurn) {
        locations = spawnPositions.stream().map(game::getStation).collect(Collectors.toList());
        for (var location : locations) {
            location.addInternalThreat(this);
        }
        return super.spawn(game, spawnTurn);
    }

    @Override
    Trajectory getTrajectory(Game game) {
        return game.getInternalTrajectory();
    }

    @Override
    protected GameLost becomeDestroyed(Game game) {
        var gameLost = super.becomeDestroyed(game);
        removeFromLocations();
        return gameLost;
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

    protected GameLost damage(Game game, int amount) {
        for (var location : locations) {
            var gameLost = location.getZone().map(x -> game.damage(x, amount)).orElse(GameLost.FALSE);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
