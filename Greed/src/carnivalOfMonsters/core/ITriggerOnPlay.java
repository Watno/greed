package carnivalOfMonsters.core;

public interface ITriggerOnPlay {
	
	public boolean triggersOn(ICanBePlayed card);
	
	public void trigger(Player player, ICanBePlayed card);
}
