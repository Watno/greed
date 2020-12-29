package spacealert.core.actionCards;

import com.fasterxml.jackson.annotation.JsonProperty;
import spacealert.core.Game;
import spacealert.core.ICrewMember;

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
		cards = Stream.generate(Optional::<ActionCard>empty)
				.limit(size)
				.collect(Collectors.toCollection(ArrayList<Optional<ActionCard>>::new));
	}

	public void place(int turn, ActionCard card) {
		int index = turn - 1;
		if (cards.get(index).isEmpty()) {
			cards.set(index, Optional.of(card));
		}
	}

	public void flipCardById(UUID cardId) {
		findCardById(cardId)
				.ifPresent(ActionCard::flip);
	}

	public Optional<ActionCard> returnCardById(UUID cardId) {
		var card = findCardById(cardId);
		if (card.isPresent()) {
			cards.remove(card);
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

	private Optional<ActionCard> findCardById(UUID cardId) {
		return cards.stream()
				.filter(Optional::isPresent)
				.map(Optional::get)
				.filter(x -> x.id.equals(cardId))
				.findFirst();
	}
}
