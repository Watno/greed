package spacealert.core.boardElements.locations;

import spacealert.core.BoardState;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
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
    protected void executeBButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {

    }

    @Override
    protected void executeCButton(BoardState boardState, ICrewMemberFromBoardStatePerspective crewMember) {

    }

}
