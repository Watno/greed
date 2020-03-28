package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class SolidEquipment extends SecretGoal {
    public SolidEquipment(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        return 2 * player.getHunterTokens();
    }

}
