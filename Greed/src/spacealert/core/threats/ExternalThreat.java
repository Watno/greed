package spacealert.core.threats;

import spacealert.core.Game;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;

import java.util.List;

public abstract class ExternalThreat extends Threat {
    private int shieldPoints;

    protected ExternalThreat(int speed, int hitPoints, int pointsForSurviving, int pointsForDestroying, int shieldPoints, Zone zone) {
        super(speed, hitPoints, pointsForSurviving, pointsForDestroying);
        this.shieldPoints = shieldPoints;
        this.zone = zone;
    }

    private Zone zone;

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

    public void assignDamageTo(int damage, DamageSource target) {
        assignedDamage += damage;
    }

    public void resolveDamage(Game game) {
        var unblockedDamage = assignedDamage - shieldPoints;
        takeDamage(game, unblockedDamage);
        assignedDamage = 0;
    }

}
