package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class TwoPerHuntToken extends SecretGoal {
    public TwoPerHuntToken(String name) {
        super(name);
    }

    @Override
    public int score(Player player) {
        return 2 * player.getHunterTokens();
    }

}
