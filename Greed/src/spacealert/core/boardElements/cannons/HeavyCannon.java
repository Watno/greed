package spacealert.core.boardElements.cannons;

import spacealert.core.boardElements.positions.Zone;

public abstract class HeavyCannon extends HeavyOrLightCannon {
    HeavyCannon(Zone zone, int damage) {
        super(zone, damage);
    }

    @Override
    public boolean usesEnergy() {
        return true;
    }
}
