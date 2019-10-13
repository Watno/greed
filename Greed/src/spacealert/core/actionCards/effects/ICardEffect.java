package spacealert.core.actionCards.effects;

import spacealert.core.Game;
import spacealert.core.ICrewMember;

public interface ICardEffect {
	void execute(ICrewMember crewmember, Game game);
}
