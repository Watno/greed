package spacealert.core.triggeredEffects;

import spacealert.core.boardElements.positions.Zone;

public class FissureXEffect extends ModifyDamageToShipEffect {
    private Zone affectedZone;

    public FissureXEffect(Zone zone) {
        this.affectedZone = zone;
    }

    @Override
    public int getModifiedDamage(Zone zone, int originalDamage) {
        if (affectedZone == zone) return originalDamage * 2;
        return originalDamage;
    }
}
