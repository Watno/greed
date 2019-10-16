package spacealert.core.actionCards;

import spacealert.core.Game;
import spacealert.core.ICrewMember;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ActionBoard {
	private int size = 12;
	private ArrayList<Optional<Card>> cards;
	private int lastExecutedTurn = 0;

	public ActionBoard() {
		cards= Stream.generate(Optional::<Card>empty)
				.limit(size)
				.collect(Collectors.toCollection(ArrayList::new));
	}

	public void place(int turn, Card card) {
		int index = turn - 1;
		if (cards.get(index).isEmpty()) {
			cards.set(index, Optional.of(card));
		}
	}

	public void execute(int turnToExecute, ICrewMember crewMember, Game game) {
		int index = turnToExecute - 1;
		cards.get(index).ifPresent(x -> x.execute(crewMember, game));
		lastExecutedTurn = turnToExecute;
	}

	public void delay(){
		delay(lastExecutedTurn +1);
	}

	private void delay(int delayedTurn) {
		if (delayedTurn > size) return;
		int index = delayedTurn+1;
		Optional<Card> cardOnTurn = cards.get(index);
		if (cardOnTurn.isPresent()) {
			delay(delayedTurn+1);
		}
		if (delayedTurn != size) {
			cards.set(index+1, cardOnTurn);	
		}
		cards.set(index, Optional.empty());
	}
}
