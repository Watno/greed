package spacealert.core.threats.templates;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.threats.Trajectory;

public abstract class Threat {
    private int speed;
    protected int maximumHitPoints;
    private int pointsForSurviving;
    private int pointsForDestroying;

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


    public GameLost advance(Game game) {
        var originalSquare = squareOnTrajectory;
        squareOnTrajectory = Math.max(originalSquare - speed, 1);
        for (var action : getTrajectory(game).getActionsBetween(originalSquare, squareOnTrajectory)) {
            switch (action) {
                case X:
                    var lostByAction = doXAction(game);
                    if (lostByAction == GameLost.TRUE) return GameLost.TRUE;
                    break;
                case Y:
                    lostByAction = doYAction(game);
                    if (lostByAction == GameLost.TRUE) return GameLost.TRUE;
                    break;
                case Z:
                    lostByAction = doZAction(game);
                    if (lostByAction == GameLost.TRUE) return GameLost.TRUE;
                    becomeSurvived(game);
                    break;
            }
        }
        return GameLost.FALSE;
    }

    protected void becomeSurvived(Game game) {
        game.recordAsSurvived(this);
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

    public void spawn(Game game, int turn) {
        squareOnTrajectory = getTrajectory(game).getStartingPosition();
        spawnTurn = turn;
        onSpawn(game);
        if (currentHitPoints == 0) {
            becomeDestroyed(game);
        }
    }

    protected void onSpawn(Game game) {

    }

    abstract Trajectory getTrajectory(Game game);

    protected abstract GameLost doXAction(Game game);

    protected abstract GameLost doYAction(Game game);

    protected abstract GameLost doZAction(Game game);

    protected void takeDamage(Game game, int damage) {
        if (damage > 0) {
            currentHitPoints -= damage;
        }
        if (currentHitPoints <= 0) becomeDestroyed(game);
    }

    protected void becomeDestroyed(Game game) {
        game.recordAsDestroyed(this);
    }

    public boolean alwaysGetsTargetedBy(DamageSource damageSource) {
        return false;
    }

    public void assignDamageTo(int damage, DamageSource source) {
    }

    protected void heal(int amount) {
        currentHitPoints = Math.min(currentHitPoints + amount, maximumHitPoints);
    }

    public void resolveDamage(Game game) {
    }
}
