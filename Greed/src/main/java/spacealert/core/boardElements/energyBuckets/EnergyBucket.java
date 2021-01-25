package spacealert.core.boardElements.energyBuckets;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.boardElements.IDamageable;
import spacealert.core.boardElements.energyBuckets.reactors.Reactor;

public abstract class EnergyBucket implements IDamageable {
    @JsonProperty
    protected int capacity;
    @JsonProperty
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

    public int drain() {
        var originalEnergy = energy;
        energy = 0;
        return originalEnergy;
    }
}
