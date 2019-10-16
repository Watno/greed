package spacealert.core.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;

import java.util.Optional;

public class Space extends Location {

    public Space() {
        super(Optional.empty());
    }

    @Override
    public boolean isSpace() {
        return true;
    }

    @Override
    protected void executeAButton(Game game, ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {

    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {

    }

}
