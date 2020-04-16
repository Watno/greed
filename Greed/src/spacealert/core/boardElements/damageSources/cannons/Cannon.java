package spacealert.core.boardElements.damageSources.cannons;

import spacealert.core.Game;
import spacealert.core.boardElements.IDamageable;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.threats.templates.Threat;

import java.util.List;

public abstract class Cannon extends DamageSource implements IDamageable {
    private boolean charged = false;

    public abstract boolean usesEnergy();

    public void charge() {
        charged = true;
    }

    public void assignDamage(List<Threat> threats, Game game) {
        if (charged) actuallyAssignDamage(game, threats);
        charged = false;
    }

    abstract void actuallyAssignDamage(Game game, List<Threat> threats);


}
