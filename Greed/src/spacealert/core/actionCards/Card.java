package spacealert.core.actionCards;

import spacealert.core.ICrewMember;

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

	public void execute(ICrewMember crewMember) {
		switch (orientation) {
			case ACTION:
				actionHalf.execute(crewMember);
			case MOVEMENT:
				movementHalf.execute(crewMember);
			default:
				break;
		}
	}
}
