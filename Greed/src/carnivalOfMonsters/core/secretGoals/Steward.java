package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class Steward extends SecretGoal {
    public Steward(String name) {
        super(name);
    }

    @Override
    public int score(Player player) {
        return (int) (player.getCardsInPlay().stream().filter(x -> x instanceof SecretGoal).count() - 1) * 2;
    }
}
