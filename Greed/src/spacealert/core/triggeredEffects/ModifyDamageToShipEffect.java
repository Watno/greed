package spacealert.core.triggeredEffects;

import spacealert.core.boardElements.positions.Zone;

public abstract class ModifyDamageToShipEffect extends TriggeredEffect {
    public abstract int getModifiedDamage(Zone zone, int originalDamage);
}
