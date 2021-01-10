package carnivalOfMonsters.core.logging;

public class PlayerScoreLogEntry extends LogEntry {
    final public String playername;
    public int score;
    public int scoreForMenagerie;
    public int scoreForSecretGoals;
    public int scoreForTrophies;
    public int scoreForCrowns;
    public int scoreForHunterTokens;
    public int scoreForLoans;


    public PlayerScoreLogEntry(String playername) {
        this.playername = playername;
    }

    public void setScores(int score, int scoreForMenagerie, int scoreForSecretGoals, int scoreForTrophies, int scoreForCrowns, int scoreForHunterTokens, int scoreForLoans) {
        this.score = score;
        this.scoreForMenagerie = scoreForMenagerie;
        this.scoreForSecretGoals = scoreForSecretGoals;
        this.scoreForTrophies = scoreForTrophies;
        this.scoreForCrowns = scoreForCrowns;
        this.scoreForHunterTokens = scoreForHunterTokens;
        this.scoreForLoans = scoreForLoans;
    }
}
