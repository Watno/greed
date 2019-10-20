package spacealert.core.boardElements.locations;

import spacealert.core.Game;
import spacealert.core.ICrewMember;
import spacealert.core.boardElements.damageSources.Rocket;
import spacealert.core.boardElements.positions.Deck;
import spacealert.core.boardElements.positions.Position;
import spacealert.core.boardElements.positions.Zone;

import java.util.ArrayList;
import java.util.List;

public class LowerBlueStation extends Location {
    private ArrayList<Rocket> rockets = new ArrayList<>(List.of(new Rocket(), new Rocket(), new Rocket()));

    LowerBlueStation() {
        super(new Position(Deck.LOWER, Zone.BLUE));
    }

    @Override
    protected void executeBButton(Game game, ICrewMember crewMember) {
        game.getReactor(Zone.BLUE).chargeFrom(game.getReactor(Zone.WHITE));
    }

    @Override
    protected void executeCButton(Game game, ICrewMember crewMember) {
        if (!rockets.isEmpty()) {
            var hasLaunched = game.tryLaunchRocket(rockets.get(0));
            if (hasLaunched) {
                rockets.remove(0);
            }
        }
    }
}
