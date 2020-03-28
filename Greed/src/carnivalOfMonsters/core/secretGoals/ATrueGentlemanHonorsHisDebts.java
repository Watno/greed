package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class ATrueGentlemanHonorsHisDebts extends SecretGoal {
    public ATrueGentlemanHonorsHisDebts(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        return 2 * player.getLoans();
    }

}
