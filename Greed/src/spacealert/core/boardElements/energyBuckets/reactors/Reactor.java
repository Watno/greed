package spacealert.core.boardElements.energyBuckets.reactors;

import spacealert.core.boardElements.energyBuckets.EnergyBucket;

public abstract class Reactor extends EnergyBucket {
    Reactor(int capacity, int energy) {
        super(capacity, energy);
    }

    public void loadToFull() {
        if (energy < capacity) capacity = energy;
    }

    public boolean tryWithdrawOneEnergy() {
        if (energy > 0) {
            energy--;
            return true;
        } else return false;
    }
}
