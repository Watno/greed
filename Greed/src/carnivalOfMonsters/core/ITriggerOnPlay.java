package carnivalOfMonsters.core;

public interface ITriggerOnPlay {
	
	public boolean triggersOn(Player player, ICanBePlayed card);
	
	public void trigger(Player player, ICanBePlayed card);
}
