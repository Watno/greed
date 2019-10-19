package spacealert.core.threats;

import spacealert.core.Game;
import spacealert.core.boardElements.positions.Zone;

public abstract class Threat {
    private int speed;
    protected int hitPoints;

    protected Threat(int speed, int hitPoints) {
        this.speed = speed;
        this.hitPoints = hitPoints;
    }

    private int distanceFromShip;
    private Zone zone;

    public void advance(Game game) {
        var originalDistance = distanceFromShip;
        distanceFromShip = Math.max(originalDistance - speed, 1);
        for (var action : getTrajectory(game).getActionsBetween(originalDistance, distanceFromShip)) {
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
        //TODO surviving threats
    }

    abstract Trajectory getTrajectory(Game game);


    abstract void doXAction(Game game);

    abstract void doYAction(Game game);

    abstract void doZAction(Game game);
}
