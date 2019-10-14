package spacealert.core.actionCards.effects;

import spacealert.core.*;
import spacealert.core.Deck;
import spacealert.core.Position;
import spacealert.core.Zone;

public abstract class CardEffect implements ICardEffect {

    @Override
    public void execute(ICrewMember crewmember, Game game) {
        if (crewmember.isInSpace()) {
            {
                if (!allowsStayingInSpace()) {
                    crewmember.moveTo(game.getStation(new Position(Deck.UPPER, Zone.RED)));
                }
            }
            executeEffect(crewmember, game);
        }
    }

    protected boolean allowsStayingInSpace() {
        return false;
    }

    abstract protected void executeEffect(ICrewMember crewMember, Game game);
}
