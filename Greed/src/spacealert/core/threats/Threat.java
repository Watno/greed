package spacealert.core.threats;

import spacealert.core.Game;

public abstract class Threat {
    private int speed;
    protected int hitPoints;
    private int pointsForSurviving;
    private int pointsForDestroying;

    protected Threat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying) {
        this.speed = speed;
        this.hitPoints = hitPoints;
        this.pointsForSurviving = pointsForSurviving;
        this.pointsForDestroying = pointsForDestroying;

    }

    private int spawnTurn;
    private int squareOnTrajectory;


    public void advance(Game game) {
        var originalSquare = squareOnTrajectory;
        squareOnTrajectory = Math.max(originalSquare - speed, 1);
        for (var action : getTrajectory(game).getActionsBetween(originalSquare, squareOnTrajectory)) {
            switch (action) {
                case X:
                    doXAction(game);
                    break;
                case Y:
                    doYAction(game);
                    break;
                case Z:
                    doZAction(game);
                    becomeSurvived(game);
                    break;
            }
        }
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
    }

    abstract Trajectory getTrajectory(Game game);

    protected abstract void doXAction(Game game);

    protected abstract void doYAction(Game game);

    protected abstract void doZAction(Game game);

    protected void takeDamage(Game game, int damage) {
        if (damage > 0) {
            hitPoints -= damage;
        }
        if (hitPoints <= 0) becomeDestroyed(game);
    }

    protected void becomeDestroyed(Game game) {
        game.recordAsDestroyed(this);
    }
}
