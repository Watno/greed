package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

import java.util.stream.Stream;

public class Specialist extends SecretGoal {

    public Specialist(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        long numberOfLandTypes = Stream.of(LandType.values())
                .filter(x -> player.getAvailableLandPoints(x) > 0)
                .count();

        if (numberOfLandTypes <= 3) return 7;

        if (numberOfLandTypes == 4) return 4;

        return 0;
    }

}
