package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

public class DangerSeeker extends SecretGoal {

    public DangerSeeker(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        return player.getMenagerie().stream()
                .mapToInt(Monster::getDangerLevel)
                .sum();
    }
}
