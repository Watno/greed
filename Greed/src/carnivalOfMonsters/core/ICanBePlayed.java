package carnivalOfMonsters.core;

public interface ICanBePlayed extends ICard {

	public boolean checkRequirement(Player playingPlayer);
	
	public void onPlay(Player playingPlayer, Game game);
}
