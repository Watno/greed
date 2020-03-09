package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;
import carnivalOfMonsters.core.monsters.Monster;

import java.util.stream.Stream;

public class RevealKeptMonsters extends Event {

    public RevealKeptMonsters(String name) {
        super(name);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onPlay(Player playingPlayer, Game game) {
        //TODO add revelation (and choice)

        playingPlayer.gainCrowns((int) Stream.of(playingPlayer.getMenagerie())
                .filter(x -> x instanceof Monster)
                .count());

    }

}
