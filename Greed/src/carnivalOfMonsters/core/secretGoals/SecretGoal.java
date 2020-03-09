package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Card;
import carnivalOfMonsters.core.ICanBeScored;
import carnivalOfMonsters.core.Player;

public abstract class SecretGoal extends Card implements ICanBeScored {

    protected SecretGoal(String name) {
        super("SecretGoal", name);
    }

    public abstract int score(Player player);

}
