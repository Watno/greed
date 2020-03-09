package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.ICard;
import carnivalOfMonsters.core.Player;

public abstract class SecretGoal implements ICard {

    public abstract int score(Player player);

}
