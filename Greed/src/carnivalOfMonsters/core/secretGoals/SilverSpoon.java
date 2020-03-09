package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class SilverSpoon extends SecretGoal {

    public SilverSpoon(String name) {
        super(name);
    }

    @Override
    public int score(Player player) {
        return 4;
    }

}
