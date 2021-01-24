package spacealert.core.threats.templates;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.Trajectory;

import java.util.List;

@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS, property = "type")
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
    Trajectory getTrajectory(BoardState boardState) {
        return boardState.getTrajectory(zone);
    }

    public boolean canBeTargetedBy(DamageSource damageSource) {
        return true;
    }

    public boolean isIn(List<Zone> zones) {
        return zones.contains(this.zone);
    }

    @Override
    public void assignDamageTo(BoardState boardState, int damage, DamageSource source) {
        assignedDamage += damage;
    }

    @Override
    public GameLost resolveDamage(BoardState boardState) {
        var unblockedDamage = assignedDamage - getEffectiveShieldPoints();
        var gameLost = takeDamage(boardState, unblockedDamage);
        resetAfterDamageResolution();
        return gameLost;
    }

    protected void resetAfterDamageResolution() {
        assignedDamage = 0;
    }

    protected int getEffectiveShieldPoints() {
        return shieldPoints;
    }

    protected GameLost attack(BoardState boardState, int amount) {
        return attack(boardState, zone, amount);
    }

    protected GameLost attack(BoardState boardState, Zone targetedZone, int amount) {
        var modifiedDamage = boardState.applyShieldsToDamage(targetedZone, amount);
        return boardState.damage(targetedZone, modifiedDamage);
    }

    protected GameLost attack(BoardState boardState, List<Zone> targetedZones, int amount) {
        for (var targetedZone : targetedZones) {
            var gameLost = attack(boardState, targetedZone, amount);
            if (gameLost == GameLost.TRUE) return GameLost.TRUE;
        }
        return GameLost.FALSE;
    }
}
