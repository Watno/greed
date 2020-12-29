package spacealert.core.boardElements.damageSources.cannons;

import spacealert.core.boardElements.positions.Zone;

public class LightCannon extends HeavyOrLightCannon {
    public LightCannon(Zone zone) {
        super(zone, 2);
    }

    @Override
    public boolean usesEnergy() {
        return false;
    }
}
