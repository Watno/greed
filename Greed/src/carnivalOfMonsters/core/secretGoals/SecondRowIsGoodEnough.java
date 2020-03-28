package carnivalOfMonsters.core.secretGoals;

import carnivalOfMonsters.core.Player;

public class SecondRowIsGoodEnough extends SecretGoal {
    public SecondRowIsGoodEnough(String name) {
        super(name);
    }

    @Override
    public int getScore(Player player) {
        //TODO score in different place
        return 0;
    }
}
