package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.LandType;
import carnivalOfMonsters.core.Player;

public class AuthorityOn extends SecretGoal {

    private LandType landType;

    public AuthorityOn(LandType landType) {
        super();
        this.landType = landType;
    }

    @Override
    public int score(Player player) {
        return (int) player.getMenagerie().stream()
                .filter(x -> x.getLandType() == landType)
                .count() * 2;
    }

}
