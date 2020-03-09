package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class EnthusiastForLittleThings extends SecretGoal {

    @Override
    public int score(Player player) {
        return (int) player.getMenagerie().stream()
                .filter(x -> x.getLevel() == 1)
                .count();
    }

}
