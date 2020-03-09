package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class ATrueGentlemanHonorsHisDebts extends SecretGoal {
    @Override
    public int score(Player player) {
        return 2 * player.getLoans();
    }

}
