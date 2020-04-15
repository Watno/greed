package spacealert.core.boardElements.locations;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.InternalThreat;

import java.util.Collection;
import java.util.Optional;

public interface ILocation {

    Collection<ICrewMember> getCrewMembers();

    Optional<Position> getPosition();

    void addCrewMember(ICrewMember crewMember);

    void removeCrewMember(ICrewMember crewMember);

    void addInternalThreat(InternalThreat threat);

    void removeInternalThreat(InternalThreat threat);

    Collection<InternalThreat> getInternalThreats();

    Optional<Zone> getZone();

    void executeButton(Game game, ICrewMember crewMember, Button button);

    boolean isSpace();
}
