package spacealert.core.actionCards.effects;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class CEffect extends CardEffect {

    protected void executeEffect(ICrewMember crewmember, Game game) {
        crewmember.executeButton(Button.C);
    }
}
