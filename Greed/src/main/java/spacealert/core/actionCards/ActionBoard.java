package spacealert.core.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Range;
import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.Phase;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActionBoard {
	@JsonProperty
	public final UUID id = UUID.randomUUID();
	@JsonProperty
	private final int size = 12;

	@JsonProperty
	private final ArrayList<Optional<ActionCard>> cards;
	private int lastExecutedTurn = 0;

	public ActionBoard() {
		cards = Stream.<Optional<ActionCard>>generate(Optional::empty)
				.limit(size)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public boolean tryPlace(int turn, ActionCard card, Optional<Phase> currentPhase) {
		int index = turn - 1;
		if (getAvailableIndices(currentPhase).contains(index) && cards.get(index).isEmpty()) {
			cards.set(index, Optional.of(card));
			return true;
		} else return false;
	}

	public void flipCardById(UUID cardId, Phase phase) {
		findCardById(cardId, Optional.of(phase))
				.ifPresent(ActionCard::flip);
	}

	public Optional<ActionCard> returnCardById(UUID cardId, Phase phase) {
		var card = findCardById(cardId, Optional.of(phase));
		if (card.isPresent()) {
			cards.set(cards.indexOf(card), Optional.empty());
		}
		return card;
	}

	public void execute(int turnToExecute, ICrewMember crewMember, Game game) {
		int index = turnToExecute - 1;
		lastExecutedTurn = turnToExecute;
		cards.get(index).ifPresent(x -> x.execute(crewMember, game));
	}

	public void delay() {
		delay(lastExecutedTurn + 1);
	}

	private void delay(int delayedTurn) {
		if (delayedTurn > size) return;
		int index = delayedTurn - 1;
		Optional<ActionCard> cardOnTurn = cards.get(index);
		if (cardOnTurn.isPresent()) {
			delay(delayedTurn + 1);
			if (delayedTurn != size) {
				cards.set(index + 1, cardOnTurn);
			}
		}
		cards.set(index, Optional.empty());
	}

	private Optional<ActionCard> findCardById(UUID cardId, Optional<Phase> phase) {
		var availableCards = getAvailableIndices(phase);
		return cards
				.subList(availableCards.lowerEndpoint(), availableCards.upperEndpoint() + 1)
				.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.filter(x -> x.id.equals(cardId))
				.findFirst();
	}

	private Range<Integer> getAvailableIndices(Optional<Phase> phase) {
		return phase.map(x -> {
			switch (x) {
				case ONE -> {return Range.closed(0, 2);}
				case TWO -> {return Range.closed(3, 6);}
				case THREE -> {return Range.closed(7, 11);}
			}
			throw new IllegalStateException();
		}).orElse(Range.closed(0, 11));
	}
}
