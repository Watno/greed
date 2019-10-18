package spacealert.core.boardElements.cannons;

import spacealert.core.Game;
import spacealert.core.boardElements.IDamageable;

public abstract class Cannon implements IDamageable {
    private boolean charged = false;

    public abstract boolean usesEnergy();

    public void charge() {
        charged = true;
    }

    public void assignDamageAndUnload(Game game) {
        if (charged) assignDamage(game);
        charged = false;
    }

    protected abstract void assignDamage(Game game);

    @Override
    public void damage() {
        //TODO
    }

}
