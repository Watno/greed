package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class BigGameHunter extends SecretGoal {
    public BigGameHunter(String name) {
        super(name);
    }

    @Override
    public int score(Player player) {
        return (int) player.getMenagerie().stream()
                .filter(x -> x.getLevel() == 3)
                .count() * 2
                +
                (int) player.getMenagerie().stream()
                        .filter(x -> x.getLevel() == 4)
                        .count() * 3;
    }
}
