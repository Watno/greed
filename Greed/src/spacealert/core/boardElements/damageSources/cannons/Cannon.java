package spacealert.core.boardElements.damageSources.cannons;

import spacealert.core.boardElements.IDamageable;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.threats.ExternalThreat;

import java.util.List;

public abstract class Cannon extends DamageSource implements IDamageable {
    private boolean charged = false;

    public abstract boolean usesEnergy();

    public void charge() {
        charged = true;
    }

    public void assignDamage(List<ExternalThreat> externalThreats) {
        if (charged) actuallyAssignDamage(externalThreats);
        charged = false;
    }

    abstract void actuallyAssignDamage(List<ExternalThreat> externalThreats);


    @Override
    public void damage() {
        //TODO
    }

}
