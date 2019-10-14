package spacealert.core;

public abstract class EnergyBucket {
    protected int capacity;
    protected int energy = 0;

    public void chargeFrom(Reactor source) {
        var availableEnergy = source.energy;
        var missingEnergy = this.capacity;

        var energyToTransfer = Math.min(availableEnergy, missingEnergy);

        source.energy -= energyToTransfer;
        this.energy += energyToTransfer;
    }
}
