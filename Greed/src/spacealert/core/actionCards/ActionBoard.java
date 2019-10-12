package spacealert.core.actionCards;

import java.util.ArrayList;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import spacealert.core.ICrewMember;

public class ActionBoard {
	private int size = 12;
	private ArrayList<Optional<Card>> cards;
	
	public ActionBoard() {
		cards= Stream.generate(() -> Optional.<Card>empty())
				.limit(size)
				.collect(Collectors.toCollection(ArrayList::new));
	}
	
	public void execute(int turnToExecute, ICrewMember crewMember) {
		int index = turnToExecute+1;
		cards.get(index).ifPresent(x -> x.execute(crewMember)); 
	}
	
	public void delay(int delayedTurn) {
		if (delayedTurn > size) return;
		int index = delayedTurn+1;
		Optional<Card> cardOnTurn = cards.get(index);
		if (cardOnTurn.isPresent()) {
			delay(delayedTurn+1);
		};
		if (delayedTurn != size) {
			cards.set(index+1, cardOnTurn);	
		}
		cards.set(index, Optional.empty());
	}
}
