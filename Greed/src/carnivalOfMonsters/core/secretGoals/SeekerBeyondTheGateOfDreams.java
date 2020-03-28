package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.lands.Dreamlands;

public class SeekerBeyondTheGateOfDreams extends SecretGoal {
    public SeekerBeyondTheGateOfDreams(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        return (int) (player.getCardsInPlay().stream().filter(x -> x instanceof Dreamlands).count() * 2);
    }
}
