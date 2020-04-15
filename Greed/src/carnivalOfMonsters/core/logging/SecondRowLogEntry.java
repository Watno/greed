package carnivalOfMonsters.core.logging;

import carnivalOfMonsters.core.secretGoals.SecondRowIsGoodEnough;

public class SecondRowLogEntry extends LogEntry {
    public final String playername;
    public final SecondRowIsGoodEnough secondRowIsGoodEnough;

    public SecondRowLogEntry(String playername, SecondRowIsGoodEnough secondRowIsGoodEnough) {
        this.playername = playername;
        this.secondRowIsGoodEnough = secondRowIsGoodEnough;
    }
}
