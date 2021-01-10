package carnivalOfMonsters.core.logging;

import carnivalOfMonsters.core.secretGoals.SecretGoal;

public class SecretGoalScoreEntry extends LogEntry {
    public final SecretGoal goal;
    public final int score;

    public SecretGoalScoreEntry(SecretGoal goal, int score) {
        this.goal = goal;
        this.score = score;
    }
}
