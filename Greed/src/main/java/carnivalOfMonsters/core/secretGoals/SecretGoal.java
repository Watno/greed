package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Card;
import carnivalOfMonsters.core.ICanBeScored;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.logging.ILogEntry;
import carnivalOfMonsters.core.logging.SecretGoalScoreEntry;

import java.util.Optional;

public abstract class SecretGoal extends Card implements ICanBeScored {

    protected SecretGoal(String name) {
        super("SecretGoal", name);
    }

    @Override
    public int score(Player player, Optional<ILogEntry> loggingContext) {
        var score = getScore(player);
        loggingContext.ifPresent(x -> x.addDependantEntry(new SecretGoalScoreEntry(this, score)));
        return score;
    }

    public abstract int getScore(Player player);

}
