package spacealert.core.actionCards.effects;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.threats.InternalThreat;
import spacealert.core.threats.Threat;

import java.util.Comparator;

public class AttackWithBattleBotEffect extends CardEffect {

    protected boolean allowsStayingInSpace() {
        return true;
    }

    @Override
    protected void executeEffect(ICrewMember crewMember, Game game) {
        if (crewMember.hasActiveBattlebot()) {
            var target = crewMember.getLocation().getInternalThreats().stream()
                    .filter(InternalThreat::canBeTargetedByBattlebot)
                    .min(Comparator.comparing(Threat::getSpawnTurn));

            target.ifPresent(x -> x.getAttackedByBattlebot(game, crewMember));

            if (target.map(InternalThreat::returnsFire).orElse(false)) {
                crewMember.disableBattleBot();
            }
        }
    }

}
