package spacealert.core;

import java.util.Collection;
import java.util.Optional;

public interface ILocation {

    Collection<ICrewMember> getCrewMembers();

    Optional<Position> getPosition();

    void addCrewMember(ICrewMember crewMember);

    void removeCrewMember(ICrewMember crewMember);

    Optional<Zone> getZone();

    void executeButton(ICrewMember crewMember, Button button);
}
