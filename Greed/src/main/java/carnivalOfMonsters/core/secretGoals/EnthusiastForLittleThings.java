package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class EnthusiastForLittleThings extends SecretGoal {

    public EnthusiastForLittleThings(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        return (int) player.getMenagerie().stream()
                .filter(x -> x.getLevel() == 1)
                .count();
    }

}
