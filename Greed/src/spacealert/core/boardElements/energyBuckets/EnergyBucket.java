package spacealert.core.boardElements.energyBuckets;

import spacealert.core.boardElements.IDamageable;
import spacealert.core.boardElements.energyBuckets.reactors.Reactor;

public abstract class EnergyBucket implements IDamageable {
    protected int capacity;
    protected int energy;

    protected EnergyBucket(int capacity, int energy) {
        this.capacity = capacity;
        this.energy = energy;
    }

    public void chargeFrom(Reactor source) {
        var availableEnergy = source.energy;
        var missingEnergy = this.capacity;

        var energyToTransfer = Math.min(availableEnergy, missingEnergy);

        source.energy -= energyToTransfer;
        this.energy += energyToTransfer;
    }

    @Override
    public void damage() {
        capacity--;
        if (energy > capacity) energy = capacity;
    }
}
