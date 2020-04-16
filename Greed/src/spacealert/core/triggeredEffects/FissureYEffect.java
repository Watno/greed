package spacealert.core.triggeredEffects;

import spacealert.core.boardElements.positions.Zone;

public class FissureYEffect extends ModifyDamageToShipEffect {

    public FissureYEffect() {
    }

    @Override
    public int getModifiedDamage(Zone zone, int originalDamage) {
        return originalDamage * 2;
    }
}
