package spacealert.core.actionCards.effects;

import spacealert.core.Direction;
import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class GravoliftMoveEffect extends CardEffect {

    protected void executeEffect(ICrewMember crewmember, Game game) {
        crewmember.moveInDirection(game, Direction.GRAVOLIFT);
        if (crewmember.getZone()
                .map(zone -> game.getGravolift(zone).causesDelay())
                .orElse(false)) {
            crewmember.delay();
        }
    }
}
