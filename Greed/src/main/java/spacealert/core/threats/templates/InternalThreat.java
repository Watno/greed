package spacealert.core.threats.templates;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spacealert.core.BoardState;
import spacealert.core.Button;
import spacealert.core.GameLost;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.locations.ILocation;
import spacealert.core.boardElements.locations.Location;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.threats.Trajectory;

import java.util.List;
import java.util.stream.Collectors;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
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
    public GameLost spawn(BoardState boardState, int spawnTurn) {
        locations = spawnPositions.stream().map(boardState::getStation).collect(Collectors.toList());
        for (var location : locations) {
            location.addInternalThreat(this);
        }
        return super.spawn(boardState, spawnTurn);
    }

    @Override
    Trajectory getTrajectory(BoardState boardState) {
        return boardState.getInternalTrajectory();
    }

    @Override
    protected GameLost becomeDestroyed(BoardState boardState) {
        var gameLost = super.becomeDestroyed(boardState);
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

    public void interceptButtonPress(BoardState boardState, Location location) {
        if (!survived) takeDamage(boardState, 1);
    }

    public boolean canBeTargetedByBattlebot() {
        return false;
    }

    public void getAttackedByBattlebot(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {
        takeDamage(boardState, 1);
    }

    public boolean returnsFire() {
        return false;
    }

    @Override
    protected void becomeSurvived(BoardState boardState) {
        super.becomeSurvived(boardState);
        survived = true;
    }

    public boolean isSurvived() {
        return survived;
    }

    protected GameLost damage(BoardState boardState, int amount) {
        for (var location : locations) {
            var gameLost = location.getZone().map(x -> boardState.damage(x, amount)).orElse(GameLost.FALSE);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
