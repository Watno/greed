package spacealert.core.actionCards.effects;

import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class AttackWithBattleBotEffect extends CardEffect {

    protected boolean allowsStayingInSpace() {
        return true;
    }

    @Override
    protected void executeEffect(ICrewMember crewMember, Game game) {

    }

}
