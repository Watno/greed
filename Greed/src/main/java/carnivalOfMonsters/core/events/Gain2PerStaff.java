package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.logging.ILogEntry;
import carnivalOfMonsters.core.staff.StaffMember;

import java.util.Optional;

public class Gain2PerStaff extends Event {
    public Gain2PerStaff(String name) {
        super(name);
    }

    @Override
    public void onPlay(Player playingPlayer, Game game, Optional<ILogEntry> loggingContext) {
        playingPlayer.gainCrowns(
                (int) playingPlayer.getCardsInPlay().stream()
                        .filter(x -> x instanceof StaffMember)
                        .count());
    }
}
