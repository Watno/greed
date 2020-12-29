package spacealert.core.actionCards.effects;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class BEffect extends CardEffect {

    protected void executeEffect(ICrewMember crewmember, Game game) {
        crewmember.executeButton(game, Button.B);
    }
}
