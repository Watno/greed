package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

import java.util.Arrays;

public class JackOfAllTrades extends SecretGoal {
    public JackOfAllTrades(String name) {
        super(name);
    }

    @Override
    public int score(Player player) {
        if (Arrays.stream(LandType.values()).allMatch(x -> player.getTotalLandPoints(x) >= 2)) {
            return 7;
        } else return 0;
    }
}
