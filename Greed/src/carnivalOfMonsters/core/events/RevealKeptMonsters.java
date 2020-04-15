package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.logging.ILogEntry;
import carnivalOfMonsters.core.logging.RevealKeptMonstersLogEntry;
import carnivalOfMonsters.core.monsters.Monster;

import java.util.Optional;
import java.util.stream.Collectors;

public class RevealKeptMonsters extends Event {

    public RevealKeptMonsters(String name) {
        super(name);
    }

    @Override
    public void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext) {

        var revealedMonsters = playingPlayer.getMenagerie().stream()
                .filter(x -> x instanceof Monster)
                .collect(Collectors.toList());

        loggingContext.ifPresent(x -> x.addDependantEntry(new RevealKeptMonstersLogEntry(revealedMonsters)));

        playingPlayer.gainCrowns(revealedMonsters.size());

    }

}
