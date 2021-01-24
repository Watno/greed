package spacealert.core.threats.implementations.external.serious.white;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.cannons.PulseCannon;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class Maelstrom extends ExternalThreat {
    public Maelstrom(Zone zone) {
        super(2, 8, 4, 8, 3, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        for (var shield : boardState.getShields()) {
            shield.drain();
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        return attack(boardState, Zone.otherThan(zone), 2);
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        return attack(boardState, Zone.otherThan(zone), 3);
    }

    private boolean hitByPulseCannon = false;

    @Override
    public void assignDamageTo(BoardState boardState, int damage, DamageSource source) {
        super.assignDamageTo(boardState, damage, source);
        if (source instanceof PulseCannon) hitByPulseCannon = true;
    }

    @Override
    protected int getEffectiveShieldPoints() {
        return hitByPulseCannon ? 0 : shieldPoints;
    }

    @Override
    protected GameLost resetAfterDamageResolution(BoardState boardState) {
        hitByPulseCannon = false;
        return super.resetAfterDamageResolution(boardState);
    }
}
