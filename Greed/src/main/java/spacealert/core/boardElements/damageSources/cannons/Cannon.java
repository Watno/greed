package spacealert.core.boardElements.damageSources.cannons;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.BoardState;
import spacealert.core.boardElements.IDamageable;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.threats.templates.Threat;

import java.util.List;

public abstract class Cannon extends DamageSource implements IDamageable {
    @JsonProperty
    private boolean charged = false;

    public abstract boolean usesEnergy();

    public void charge() {
        charged = true;
    }

    public void assignDamage(List<Threat> threats, BoardState boardState) {
        if (charged) actuallyAssignDamage(boardState, threats);
        charged = false;
    }

    abstract void actuallyAssignDamage(BoardState boardState, List<Threat> threats);


}
