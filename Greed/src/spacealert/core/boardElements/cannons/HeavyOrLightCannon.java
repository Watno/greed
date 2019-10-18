package spacealert.core.boardElements.cannons;

import spacealert.core.Game;
import spacealert.core.boardElements.positions.Zone;

public abstract class HeavyOrLightCannon extends Cannon {
    private Zone zone;
    private int damage;

    HeavyOrLightCannon(Zone zone, int damage) {
        this.zone = zone;
        this.damage = damage;
    }

    @Override
    public void assignDamage(Game game) {
//TODO
    }
}
