package spacealert.core.boardElements.locations;

import spacealert.core.BoardState;
import spacealert.core.Button;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.InternalThreat;

import java.util.Collection;
import java.util.Optional;

public interface ILocation {

    Collection<ICrewMemberFromBoardStatePerspective> getCrewMembers();

    Optional<Position> getPosition();

    void addCrewMember(ICrewMemberFromBoardStatePerspective crewMember);

    void removeCrewMember(ICrewMemberFromBoardStatePerspective crewMember);

    void addInternalThreat(InternalThreat threat);

    void removeInternalThreat(InternalThreat threat);

    Collection<InternalThreat> getInternalThreats();

    Optional<Zone> getZone();

    void executeButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember, Button button);

    boolean isSpace();
}
