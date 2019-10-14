package spacealert.core;

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
