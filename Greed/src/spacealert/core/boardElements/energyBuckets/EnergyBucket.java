package spacealert.core.boardElements.energyBuckets;

import spacealert.core.boardElements.energyBuckets.reactors.Reactor;

public abstract class EnergyBucket {
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
}
