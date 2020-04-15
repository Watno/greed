package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class DangerSeeker extends SecretGoal {

    public DangerSeeker(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        return player.getMenagerie().stream()
                .mapToInt(x -> x.getDangerLevel())
                .sum();
    }
}
