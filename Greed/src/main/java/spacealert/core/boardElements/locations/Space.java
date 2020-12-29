package spacealert.core.boardElements.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.damageSources.Interceptors;

import java.util.Optional;

public class Space extends Location {

    public Space() {
        super(Optional.empty());
    }

    public Optional<Interceptors> getInterceptors() {
        if (!getCrewMembers().isEmpty()) {
            return Optional.of(new Interceptors());
        } else return Optional.empty();
    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {

    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {

    }

}
