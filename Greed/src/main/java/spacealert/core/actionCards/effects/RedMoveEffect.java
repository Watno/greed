package spacealert.core.actionCards.effects;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Direction;

public class RedMoveEffect extends CardEffect {
    protected void executeEffect(ICrewMember crewmember, Game game) {
        crewmember.moveInDirection(game, Direction.RED);
    }
}
