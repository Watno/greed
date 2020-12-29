package carnivalOfMonsters.core;

public interface ITriggerOnPlay {

    boolean triggersOn(Player player, ICanBePlayed card);

    void trigger(Player player, ICanBePlayed card);
}
