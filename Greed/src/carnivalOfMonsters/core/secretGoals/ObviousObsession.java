package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

import java.util.stream.Stream;

public class ObviousObsession extends SecretGoal {

    public ObviousObsession(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        if (Stream.of(LandType.values())
                .anyMatch(x -> player.getAvailableLandPoints(x) >= 7)) {
            return 7;
        }
        return 0;
    }

}
