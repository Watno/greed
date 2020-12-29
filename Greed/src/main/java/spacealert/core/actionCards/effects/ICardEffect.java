package spacealert.core.actionCards.effects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spacealert.core.Game;
import spacealert.core.ICrewMember;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface ICardEffect {
    void execute(ICrewMember crewmember, Game game);
}
