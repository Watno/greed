package spacealert.core.actionCards;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.actionCards.effects.ICardEffect;

public class Card {
	private ICardEffect actionHalf;
	private ICardEffect movementHalf;
	private CardOrientation orientation;

	public Card(ICardEffect actionHalf, ICardEffect movementHalf) {
		this(actionHalf, movementHalf, CardOrientation.ACTION);
	}

	public Card(ICardEffect actionHalf, ICardEffect movementHalf, CardOrientation orientation) {
		super();
		this.actionHalf = actionHalf;
		this.movementHalf = movementHalf;
		this.orientation = orientation;
	}

    public void execute(ICrewMember crewMember, Game game) {
        var activeHalf = getActiveHalf();
        activeHalf.execute(crewMember, game);
    }

    private ICardEffect getActiveHalf() {
        switch (orientation) {
            case ACTION:
                return actionHalf;
            case MOVEMENT:
                return movementHalf;
            default:
                throw new IllegalArgumentException();
        }
    }
}
