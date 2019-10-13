package spacealert.core.actionCards.effects;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class AEffect implements ICardEffect {

    @Override
    public void execute(ICrewMember crewmember, Game game) {
        crewmember.executeButton(Button.A);
    }
}
