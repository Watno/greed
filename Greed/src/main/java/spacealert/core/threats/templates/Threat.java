package spacealert.core.threats.templates;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.threats.Trajectory;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "type")
public abstract class Threat {
    protected int speed;
    protected int maximumHitPoints;
    private final int pointsForSurviving;
    private final int pointsForDestroying;

    protected Threat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying) {
        this.speed = speed;
        this.maximumHitPoints = hitPoints;
        this.currentHitPoints = hitPoints;
        this.pointsForSurviving = pointsForSurviving;
        this.pointsForDestroying = pointsForDestroying;

    }

    protected int currentHitPoints;

    private int spawnTurn;
    private int squareOnTrajectory;


    public GameLost advance(BoardState boardState) {
        var originalSquare = squareOnTrajectory;
        squareOnTrajectory = Math.max(originalSquare - speed, 1);
        for (var action : getTrajectory(boardState).getActionsBetween(originalSquare, squareOnTrajectory)) {
            switch (action) {
                case X:
                    var lostByAction = doXAction(boardState);
                    if (lostByAction == GameLost.TRUE) return GameLost.TRUE;
                    break;
                case Y:
                    lostByAction = doYAction(boardState);
                    if (lostByAction == GameLost.TRUE) return GameLost.TRUE;
                    break;
                case Z:
                    lostByAction = doZAction(boardState);
                    if (lostByAction == GameLost.TRUE) return GameLost.TRUE;
                    becomeSurvived(boardState);
                    break;
            }
        }
        return GameLost.FALSE;
    }

    protected void becomeSurvived(BoardState boardState) {
        boardState.recordAsSurvived(this);
    }

    public int getSquareOnTrajectory() {
        return squareOnTrajectory;
    }

    public int getSpawnTurn() {
        return spawnTurn;
    }

    public int getDistance() {
        if (squareOnTrajectory > 10) return 3;
        if (squareOnTrajectory > 5) return 2;
        return 1;
    }

    public GameLost spawn(BoardState boardState, int turn) {
        squareOnTrajectory = getTrajectory(boardState).getStartingPosition();
        spawnTurn = turn;
        onSpawn(boardState);
        if (currentHitPoints == 0) {
            return becomeDestroyed(boardState);
        }
        return GameLost.FALSE;
    }

    protected void onSpawn(BoardState boardState) {

    }

    abstract Trajectory getTrajectory(BoardState boardState);

    protected abstract GameLost doXAction(BoardState boardState);

    protected abstract GameLost doYAction(BoardState boardState);

    protected abstract GameLost doZAction(BoardState boardState);

    public GameLost takeDamage(BoardState boardState, int damage) {
        if (damage > 0) {
            currentHitPoints -= damage;
        }
        if (currentHitPoints <= 0) return becomeDestroyed(boardState);
        else return GameLost.FALSE;
    }

    protected GameLost becomeDestroyed(BoardState boardState) {
        boardState.recordAsDestroyed(this);
        return onDestroyed(boardState);
    }

    protected GameLost onDestroyed(BoardState boardState) {
        return GameLost.FALSE;
    }

    public boolean alwaysGetsTargetedBy(DamageSource damageSource) {
        return false;
    }

    public void assignDamageTo(BoardState boardState, int damage, DamageSource source) {
    }

    protected void heal(int amount) {
        currentHitPoints = Math.min(currentHitPoints + amount, maximumHitPoints);
    }

    public GameLost resolveDamage(BoardState boardState) {
        return GameLost.FALSE;
    }

    public int getPointsForSurviving() {
        return pointsForSurviving;
    }

    public int getPointsForDestroying() {
        return pointsForDestroying;
    }

    protected int getDamageTaken() {
        return maximumHitPoints - currentHitPoints;
    }
}
