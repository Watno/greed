package spacealert.core.threats.templates;

import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.Trajectory;

import java.util.List;

public abstract class ExternalThreat extends Threat {
    protected int shieldPoints;

    protected ExternalThreat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, int shieldPoints, Zone zone) {
        super(speed, hitPoints, pointsForSurviving, pointsForDestroying);
        this.shieldPoints = shieldPoints;
        this.zone = zone;
    }

    protected Zone zone;

    private int assignedDamage = 0;

    @Override
    Trajectory getTrajectory(Game game) {
        return game.getTrajectory(zone);
    }

    public boolean alwaysGetsTargetedBy(DamageSource damageSource) {
        return false;
    }

    public boolean canBeTargetedBy(DamageSource damageSource) {
        return true;
    }

    public boolean isIn(List<Zone> zones) {
        return zones.contains(this.zone);
    }

    public void assignDamageTo(int damage, DamageSource source) {
        assignedDamage += damage;
    }

    public void resolveDamage(Game game) {
        var unblockedDamage = assignedDamage - getEffectiveShieldPoints();
        takeDamage(game, unblockedDamage);
        resetAfterDamageResolution();
    }

    protected void resetAfterDamageResolution() {
        assignedDamage = 0;
    }

    protected int getEffectiveShieldPoints() {
        return shieldPoints;
    }

    protected GameLost attack(Game game, int amount) {
        return attack(game, zone, amount);
    }

    protected GameLost attack(Game game, Zone targetedZone, int amount) {
        var modifiedDamage = game.applyShieldsToDamage(targetedZone, amount);
        return game.damage(targetedZone, modifiedDamage);
    }

    protected GameLost attack(Game game, List<Zone> targetedZones, int amount) {
        for (var targetedZone : targetedZones) {
            var gameLost = attack(game, targetedZone, amount);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }

    protected void heal(int amount) {
        currentHitPoints = Math.min(currentHitPoints + amount, maximumHitPoints);
    }
}
