package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

import java.util.stream.Stream;

public class SevenIfSevenInLandType extends SecretGoal {

    public SevenIfSevenInLandType(String name) {
        super(name);
    }

    @Override
    public int score(Player player) {
        if (Stream.of(LandType.values())
                .anyMatch(x -> player.getAvailableLandPoints(x) >= 7)) {
            return 7;
        }
        return 0;
    }

}
