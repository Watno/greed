package carnivalOfMonsters.core.logging;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PlayerScoreLogEntry extends LogEntry {
    @JsonProperty
    final public String playername;
    @JsonProperty
    final public int score;

    public PlayerScoreLogEntry(String playername, int score) {
        this.playername = playername;
        this.score = score;
    }
}
