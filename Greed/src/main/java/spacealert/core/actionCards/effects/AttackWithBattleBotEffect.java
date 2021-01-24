package spacealert.core.actionCards.effects;

import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.threats.templates.InternalThreat;
import spacealert.core.threats.templates.Threat;

import java.util.Comparator;

public class AttackWithBattleBotEffect extends CardEffect {

    protected boolean allowsStayingInSpace() {
        return true;
    }

    @Override
    protected void executeEffect(ICrewMemberFromBoardStatePerspective crewMember, BoardState boardState) {
        if (crewMember.hasActiveBattlebot()) {
            var target = crewMember.getLocation().getInternalThreats().stream()
                    .filter(InternalThreat::canBeTargetedByBattlebot)
                    .min(Comparator.comparing(Threat::getSpawnTurn));

            target.ifPresent(x -> x.getAttackedByBattlebot(boardState, crewMember));

            if (target.map(InternalThreat::returnsFire).orElse(false)) {
                crewMember.disableBattleBot();
            }
        }
    }

}
