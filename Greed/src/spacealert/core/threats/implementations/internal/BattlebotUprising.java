package spacealert.core.threats.implementations.internal;

import spacealert.core.Button;
import spacealert.core.Game;
import spacealert.core.GameLost;
import spacealert.core.ICrewMember;
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
    protected GameLost doXAction(Game game) {
        game.getCrewMembers().stream().filter(ICrewMember::hasActiveBattlebot)
                .forEach(ICrewMember::becomeKnockedOut);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doYAction(Game game) {
        locations.stream().flatMap(x -> x.getCrewMembers().stream())
                .forEach(ICrewMember::becomeKnockedOut);
        return GameLost.FALSE;
    }

    @Override
    protected GameLost doZAction(Game game) {
        //noinspection OptionalGetWithoutIsPresent
        game.getCrewMembers().stream().filter(x -> !x.getLocation().getPosition()
                .get().equals(new Position(Deck.UPPER, Zone.WHITE)))
                .forEach(ICrewMember::becomeKnockedOut);
        return GameLost.FALSE;
    }

    HashSet<Location> pressedLocations = new HashSet<>();

    @Override
    public void interceptButtonPress(Game game, Location location) {
        super.interceptButtonPress(game, location);
        var added = pressedLocations.add(location);
        if (added && pressedLocations.equals(new HashSet<>(locations))) {
            takeDamage(game, 1); //should actually happen at end of Player Actions
        }

    }
}
