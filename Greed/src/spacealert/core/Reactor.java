package spacealert.core;

public abstract class Reactor extends EnergyBucket {
    public void loadToFull() {
        if (energy < capacity) capacity = energy;
    }
}
