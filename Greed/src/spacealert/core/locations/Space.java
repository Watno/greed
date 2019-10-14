package spacealert.core.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;

public class Space extends Location {

    public Space(Game game) {
        super(game);
    }

    @Override
    public boolean isSpace() {
        return true;
    }

    @Override
    protected void executeAButton(ICrewMember crewMember) {

    }

    @Override
    protected void executeBButton(ICrewMember crewMember) {

    }

    @Override
    protected void executeCButton(ICrewMember crewMember) {

    }

}
