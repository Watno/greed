package carnivalOfMonsters.core;

public interface ICanBePlayed extends ICard {

    boolean checkRequirement(Player playingPlayer);

    void onPlay(Player playingPlayer, Game game);
}
