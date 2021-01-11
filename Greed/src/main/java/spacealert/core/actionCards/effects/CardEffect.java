package spacealert.core.actionCards.effects;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public abstract class CardEffect implements ICardEffect {

    @Override
    public void execute(ICrewMember crewmember, Game game) {
        if (crewmember.isInSpace() && !allowsStayingInSpace()) {
            {
                crewmember.moveTo(game.getStation(new Position(Deck.UPPER, Zone.RED)));
                crewmember.delay();
                return;
            }
        }
        executeEffect(crewmember, game);
    }

    protected boolean allowsStayingInSpace() {
        return false;
    }

    abstract protected void executeEffect(ICrewMember crewMember, Game game);
}
