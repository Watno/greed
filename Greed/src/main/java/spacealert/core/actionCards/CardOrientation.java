package spacealert.core.actionCards;

public enum CardOrientation {
	ACTION,
	MOVEMENT;
	
	public CardOrientation flip() {
		switch (this) {
		case ACTION:
			return MOVEMENT;
		case MOVEMENT:
			return ACTION;
		default:
			throw new IllegalArgumentException();
		}
	}
}
