package carnivalOfMonsters.core.events;

import carnivalOfMonsters.core.Game;
import carnivalOfMonsters.core.Player;

public class GainHunterToken extends Event {

    public GainHunterToken(String name) {
        super(name);
    }

    @Override
    public void onPlay(Player playingPlayer, Game game) {
        playingPlayer.gainHunterToken();
    }

}
