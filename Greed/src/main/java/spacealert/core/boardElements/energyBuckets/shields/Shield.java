package spacealert.core.boardElements.energyBuckets.shields;

import spacealert.core.boardElements.energyBuckets.EnergyBucket;

public class Shield extends EnergyBucket {
    Shield(int capacity) {
        super(capacity, 1);
    }

    public int blockDamage(int amount) {
        var blockedDamage = Math.min(energy, amount);
        energy -= blockedDamage;
        return amount - blockedDamage;
    }

}
