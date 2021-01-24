package spacealert.core.actionCards.effects;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
public interface ICardEffect {
    void execute(ICrewMemberFromBoardStatePerspective crewmember, BoardState boardState);
}
