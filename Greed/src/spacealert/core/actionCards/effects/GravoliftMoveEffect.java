package spacealert.core.actionCards.effects;

import spacealert.core.Direction;
import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class GravoliftMoveEffect implements ICardEffect {

    @Override
    public void execute(ICrewMember crewmember, Game game) {
        crewmember.moveInDirection(Direction.GRAVOLIFT);
        if (crewmember.getZone().map(x -> game.getGravolifts().get(x).causesDelay()).orElse(false)){
            crewmember.delay();
        }
    }
}
