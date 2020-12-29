package carnivalOfMonsters.core.logging;

import carnivalOfMonsters.core.monsters.Monster;

import java.util.Collection;

public class RevealKeptMonstersLogEntry extends LogEntry {
    public final Collection<Monster> revealedMonsters;

    public RevealKeptMonstersLogEntry(Collection<Monster> revealedMonsters) {
        this.revealedMonsters = revealedMonsters;
    }
}
