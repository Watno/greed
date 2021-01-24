package spacealert.core.threats.implementations.internal;

import spacealert.core.BoardState;
import spacealert.core.Button;
import spacealert.core.GameLost;
import spacealert.core.ICrewMemberFromBoardStatePerspective;
import spacealert.core.boardElements.locations.Location;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;
import spacealert.core.threats.templates.Malfunction;

import java.util.HashSet;
import java.util.List;

public class BattlebotUprising extends Malfunction {
    public BattlebotUprising() {
        super(2, 4, 4, 8, List.of(new Position(Deck.UPPER, Zone.RED), new Position(Deck.LOWER, Zone.BLUE)), Button.C);
    }

    @Override
    protected GameLost doXAction(BoardState boardState) {
        boardState.getCrewMembers().stream().filter(ICrewMemberFromBoardStatePerspective::hasActiveBattlebot)
                .forEach(ICrewMemberFromBoardStatePerspective::becomeKnockedOut);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(BoardState boardState) {
        locations.stream().flatMap(x -> x.getCrewMembers().stream())
                .forEach(ICrewMemberFromBoardStatePerspective::becomeKnockedOut);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(BoardState boardState) {
        //noinspection OptionalGetWithoutIsPresent
        boardState.getCrewMembers().stream().filter(x -> !x.getLocation().getPosition()
                .get().equals(new Position(Deck.UPPER, Zone.WHITE)))
                .forEach(ICrewMemberFromBoardStatePerspective::becomeKnockedOut);
        return GameLost.FALSE;
    }

    HashSet<Location> pressedLocations = new HashSet<>();

    @Override
    public void interceptButtonPress(BoardState boardState, Location location) {
        super.interceptButtonPress(boardState, location);
        var added = pressedLocations.add(location);
        if (added && pressedLocations.equals(new HashSet<>(locations))) {
            takeDamage(boardState, 1); //should actually happen at end of Player Actions
        }

    }
}
