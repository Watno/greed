package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.cannons.PulseCannon;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

public class EnergyCloud extends ExternalThreat {
    public EnergyCloud(Zone zone) {
        super(2, 5, 2, 4, 3, zone);
    }

    @Override
    protected GameLost doXAction(Game game) {
        for (var shield : game.getShields()) {
            shield.drain();
        }
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        return attack(game, Zone.otherThan(zone), 1);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, Zone.otherThan(zone), 2);
    }

    private boolean hitByPulseCannon = false;

    @Override
    public void assignDamageTo(Game game, int damage, DamageSource source) {
        super.assignDamageTo(game, damage, source);
        if (source instanceof PulseCannon) hitByPulseCannon = true;
    }

    @Override
    protected int getEffectiveShieldPoints() {
        return hitByPulseCannon ? 0 : shieldPoints;
    }

    @Override
    protected void resetAfterDamageResolution() {
        super.resetAfterDamageResolution();
        hitByPulseCannon = false;
    }
}
