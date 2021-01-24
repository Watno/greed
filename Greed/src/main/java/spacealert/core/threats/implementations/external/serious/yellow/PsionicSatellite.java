package spacealert.core.threats.implementations.external.serious.yellow;

import spacealert.core.BoardState;
import spacealert.core.GameLost;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.damageSources.DamageSource;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.ExternalThreat;

import java.util.Optional;

public class PsionicSatellite extends ExternalThreat {
    public PsionicSatellite(Zone zone) {
        super(2, 5, 6, 12, 2, zone);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        boardState.getCrewMembers().stream().filter(x -> x.getZone().equals(Optional.of(zone)))
                .forEach(ICrewMemberFromBoardStatePerspective::delay);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        boardState.getCrewMembers().stream().filter(x -> !x.isInSpace())
                .forEach(ICrewMemberFromBoardStatePerspective::delay);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        boardState.getCrewMembers().stream().filter(x -> !x.isInSpace())
                .forEach(ICrewMemberFromBoardStatePerspective::becomeKnockedOut);
        return GameLost.FALSE;
    }

    @Override
    public boolean canBeTargetedBy(DamageSource damageSource) {
        return getDistance() != 3;
    }
}
