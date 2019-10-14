package spacealert.core.locations;

import spacealert.core.Button;
import spacealert.core.ICrewMember;
import spacealert.core.Position;
import spacealert.core.Zone;

import java.util.Collection;
import java.util.Optional;

public interface ILocation {

    Collection<ICrewMember> getCrewMembers();

    Optional<Position> getPosition();

    void addCrewMember(ICrewMember crewMember);

    void removeCrewMember(ICrewMember crewMember);

    Optional<Zone> getZone();

    void executeButton(ICrewMember crewMember, Button button);

    boolean isSpace();
}
