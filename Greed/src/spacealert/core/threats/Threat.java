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

        squareOnTrajectory = 15;
        //TODO fix to proper
    }

    private int squareOnTrajectory;
//TODO initialize

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
                    break;
            }
        }
        game.recordAsSurvived(this);
    }

    public int getSquareOnTrajectory() {
        return squareOnTrajectory;
    }

    public int getDistance() {
        if (squareOnTrajectory > 10) return 3;
        if (squareOnTrajectory > 5) return 2;
        return 1;
    }

    abstract Trajectory getTrajectory(Game game);

    protected abstract void doXAction(Game game);

    protected abstract void doYAction(Game game);

    protected abstract void doZAction(Game game);
}
