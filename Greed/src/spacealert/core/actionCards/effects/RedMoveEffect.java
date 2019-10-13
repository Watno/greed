package spacealert.core.actionCards.effects;

import spacealert.core.Direction;
import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class RedMoveEffect implements ICardEffect {
    @Override
    public void execute(ICrewMember crewmember, Game game) {
        crewmember.moveInDirection(Direction.RED);
    }
}
