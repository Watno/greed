package spacealert.core.boardElements.positions;

public enum Deck {
	UPPER,
	LOWER;

	public Deck otherDeck() {
		switch (this) {
			case LOWER:
				return UPPER;
			case UPPER:
				return LOWER;
			default:
				throw new IllegalArgumentException();
		}
	}
}
