package spacealert.core.actionCards.effects;

import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

public abstract class CardEffect implements ICardEffect {

    @Override
    public void execute(ICrewMemberFromBoardStatePerspective crewmember, BoardState boardState) {
        if (crewmember.isInSpace() && !allowsStayingInSpace()) {
            {
                crewmember.moveTo(boardState.getStation(new Position(Deck.UPPER, Zone.RED)));
                crewmember.delay();
                return;
            }
        }
        executeEffect(crewmember, boardState);
    }

    protected boolean allowsStayingInSpace() {
        return false;
    }

    abstract protected void executeEffect(ICrewMemberFromBoardStatePerspective crewMember, BoardState boardState);
}
