package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class ThriftIsAVirtue extends SecretGoal {
    public ThriftIsAVirtue(String name) {
        super(name);
    }

    @Override
    public int score(Player player) {
        if (player.getLoans() == 0) return 6;
        else return 0;
    }
}
