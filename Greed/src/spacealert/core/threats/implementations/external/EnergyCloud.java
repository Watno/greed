package spacealert.core.threats.implementations.external;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.damageSources.cannons.PulseCannon;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        return attack(game, getOtherZones(), 1);
    }

    @Override
    protected GameLost doZAction(Game game) {
        return attack(game, getOtherZones(), 2);
    }

    private List<Zone> getOtherZones() {
        return Arrays.stream(Zone.values()).filter(x -> x != this.zone).collect(Collectors.toList());
    }

    private boolean hitByPulseCannon = false;

    @Override
    public void assignDamageTo(int damage, DamageSource source) {
        super.assignDamageTo(damage, source);
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
